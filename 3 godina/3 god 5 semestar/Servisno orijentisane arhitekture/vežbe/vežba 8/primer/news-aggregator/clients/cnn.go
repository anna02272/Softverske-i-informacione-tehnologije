package clients

import (
	"context"
	"encoding/json"
	"fmt"
	"net/http"
	"soa/2023-2024/lab7/news-aggregator/domain"
	"time"

	"github.com/eapache/go-resiliency/retrier"
	"github.com/sony/gobreaker"
)

type CNNClient struct {
	client  *http.Client
	address string
	cb      *gobreaker.CircuitBreaker
}

func NewCNNClient(client *http.Client, address string, cb *gobreaker.CircuitBreaker) domain.NewsSource {
	return CNNClient{
		client:  client,
		address: address,
		cb:      cb,
	}
}

func (c CNNClient) Search(ctx context.Context, query string) ([]domain.Article, error) {
	reqUrl := fmt.Sprintf("%s/articles?query=%s", c.address, query)
	// we extract the timeout from the ctx if it was set
	var timeout time.Duration
	deadline, reqHasDeadline := ctx.Deadline()
	// if an error that occured during the execution of the http request
	// is considered temporary, we will retry the request
	classifier := retrier.WhitelistClassifier{domain.ErrRespTmp{}}
	retrier := retrier.New(retrier.ConstantBackoff(3, 100*time.Millisecond), classifier)

	searchResp := SearchResp{}
	err := retrier.RunCtx(ctx, func(ctx context.Context) error {
		// this function is called on every retry

		// we extract the timeout from the ctx if it was set
		if reqHasDeadline {
			timeout = time.Until(deadline)
		}
		// the http request is wrapped in a circuit breaker's Execute method
		// if cb is in the open state, it wan't even call our function
		// and will just return an error
		cbResp, err := c.cb.Execute(func() (interface{}, error) {
			// we set the context for the request
			// because if we have a timeout set in the context
			// the http request will abort if we exceed that timeout
			req, err := http.NewRequestWithContext(ctx, http.MethodGet, reqUrl, nil)
			if err != nil {
				return nil, err
			}
			return c.client.Do(req)
		})
		if err != nil {
			return handleHttpReqErr(err, reqUrl, http.MethodGet, timeout)
		}

		resp := cbResp.(*http.Response)

		// we treat error status codes differently from the BBC case
		// and differentiate between errors that we consider to be temporary
		// and those we don't

		// if the service is beeing too slow or can't accept connections
		// we'll treat those errors as potentially temporary
		if resp.StatusCode == http.StatusGatewayTimeout || resp.StatusCode == http.StatusServiceUnavailable {
			return domain.ErrRespTmp{
				URL:        resp.Request.URL.String(),
				Method:     resp.Request.Method,
				StatusCode: resp.StatusCode,
			}
		}
		// we don't consider other types of errors to be temporary
		if resp.StatusCode != http.StatusOK {
			return domain.ErrResp{
				URL:        resp.Request.URL.String(),
				Method:     resp.Request.Method,
				StatusCode: resp.StatusCode,
			}
		}
		// if everything went fine, we'll try to read the response body
		err = json.NewDecoder(resp.Body).Decode(&searchResp)
		if err != nil {
			return domain.ErrInternal{InnerErr: err}
		}
		return nil
	})

	if err != nil {
		return nil, err
	}
	return toDomainArticles(searchResp, "CNN"), nil
}
