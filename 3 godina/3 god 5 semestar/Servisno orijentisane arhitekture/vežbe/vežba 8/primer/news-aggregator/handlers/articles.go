package handlers

import (
	"context"
	"log"
	"net/http"
	"soa/2023-2024/lab7/news-aggregator/domain"
	"strconv"
	"time"
)

type ArticleHandler struct {
	sources []domain.NewsSource
}

func NewArticleHandler(sources []domain.NewsSource) ArticleHandler {
	return ArticleHandler{
		sources: sources,
	}
}

func (h ArticleHandler) Search(w http.ResponseWriter, r *http.Request) {
	query := r.URL.Query().Get("query")
	log.Println("search query: " + query)

	// if the timeout is set, we have to return the response
	// to the client in 'Timeout' milliseconds
	timeoutStr := r.Header["Timeout"]
	// we don't have a timeout set
	if len(timeoutStr) == 0 {
		h.searchWithoutGlobalTimeout(w, r, query)
		return
	}
	timeout, err := strconv.Atoi(timeoutStr[0])
	// we didn't set the timeout correctly
	// so we treat it as though the header wasn't set
	if err != nil {
		h.searchWithoutGlobalTimeout(w, r, query)
		return
	}

	// we have a timeout set
	ctx, cancel := context.WithTimeout(r.Context(), time.Duration(timeout)*time.Millisecond)
	defer cancel()
	r = r.WithContext(ctx)
	h.searchWithGlobalTimeout(w, r, query)
}

func (h ArticleHandler) searchWithoutGlobalTimeout(w http.ResponseWriter, r *http.Request, query string) {
	articles := make([]domain.Article, 0)

	for _, source := range h.sources {
		// we set a default timeout of 5s for each request to a news source
		ctx, cancel := context.WithTimeout(r.Context(), 5000*time.Millisecond)
		defer cancel()
		articlesFromSource, err := source.Search(ctx, query)
		if err != nil {
			log.Println(err)
			continue
		}
		articles = append(articles, articlesFromSource...)
	}
	resp := SearchResp{
		Total:    len(articles),
		Articles: articles,
	}
	writeResp(resp, http.StatusOK, w)
}

func (h ArticleHandler) searchWithGlobalTimeout(w http.ResponseWriter, r *http.Request, query string) {
	articles := make([]domain.Article, 0)

	for _, source := range h.sources {
		select {
		case <-r.Context().Done():
			// timeout
			// we can signal that we haven't managed to collect articles from all sources
			// writeResp(nil, http.StatusGatewayTimeout, w)
			// or we can return a partial result set
			resp := SearchResp{
				Articles: articles,
			}
			writeResp(resp, http.StatusOK, w)
			return
		default:
			articlesFromSource, err := source.Search(r.Context(), query)
			if err != nil {
				log.Println(err)
				continue
			}
			articles = append(articles, articlesFromSource...)
		}
	}
	resp := SearchResp{
		Total:    len(articles),
		Articles: articles,
	}
	writeResp(resp, http.StatusOK, w)
}
