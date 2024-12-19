package clients

import (
	"context"
	"encoding/json"
	"fmt"
	"net/http"
	"soa/2023-2024/lab7/news-aggregator/domain"
	"time"

	"github.com/sony/gobreaker"
)

type BBCClient struct {
	client  *http.Client
	address string
	cb      *gobreaker.CircuitBreaker
}

func NewBBCClient(client *http.Client, address string, cb *gobreaker.CircuitBreaker) domain.NewsSource {
	return BBCClient{
		client:  client,
		address: address,
		cb:      cb,
	}
}

func (c BBCClient) Search(ctx context.Context, query string) ([]domain.Article, error) {
	reqUrl := fmt.Sprintf("%s/articles?query=%s", c.address, query)
	// we extract the timeout from the ctx if it was set
	var timeout time.Duration
	deadline, reqHasDeadline := ctx.Deadline()
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
		return nil, handleHttpReqErr(err, reqUrl, http.MethodGet, timeout)
	}

	resp := cbResp.(*http.Response)
	if resp.StatusCode != http.StatusOK {
		return nil, domain.ErrResp{
			URL:        resp.Request.URL.String(),
			Method:     resp.Request.Method,
			StatusCode: resp.StatusCode,
		}
	}
	// if everything went fine, we'll try to read the response body
	searchResp := SearchResp{}
	err = json.NewDecoder(resp.Body).Decode(&searchResp)
	if err != nil {
		return nil, domain.ErrInternal{InnerErr: err}
	}
	return toDomainArticles(searchResp, "BBC"), nil
}
