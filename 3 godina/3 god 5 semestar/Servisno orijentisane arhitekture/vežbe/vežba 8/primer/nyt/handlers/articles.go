package handlers

import (
	"context"
	"log"
	"net/http"
	"soa/2023-2024/lab7/nyt/domain"
	"strconv"
	"strings"
	"time"
)

type ArticleHandler struct {
	articles []domain.Article
}

func NewArticleHandler(articles []domain.Article) *ArticleHandler {
	return &ArticleHandler{
		articles: articles,
	}
}

func (h *ArticleHandler) Search(w http.ResponseWriter, r *http.Request) {
	timeoutStr, ok := r.Header["Timeout"]
	ctx := r.Context()
	if ok {
		timeout, err := strconv.Atoi(timeoutStr[0])
		if err == nil {
			timeoutCtx, cancel := context.WithTimeout(r.Context(), time.Duration(timeout)*time.Millisecond)
			defer cancel()
			ctx = timeoutCtx
			log.Printf("request timeout: %d ms\n", timeout)
		}
	}

	query := r.URL.Query().Get("query")

	articles := h.search(ctx, query)
	// articles will be nil if the request timed out
	// so we don't have to write the response
	if articles == nil {
		return
	}
	resp := SearchResp{
		Articles: articles,
	}
	writeResp(resp, http.StatusOK, w)
}

func (h *ArticleHandler) search(ctx context.Context, query string) []domain.Article {
	articles := make([]domain.Article, 0)

	for _, article := range h.articles {
		select {
		// this code will run if the timeout was exceeded
		case <-ctx.Done():
			// we give up on processing the request as the client disconnected
			log.Println("request timed out")
			return nil
		// this code will run if the timeout hasn't been exceeded yet
		default:
			log.Println("NYT - processing article: " + article.Title)
			// we simulate the time needed to retrieve and search the text
			time.Sleep(500 * time.Millisecond)
			title := strings.ToLower(article.Title)
			contents := strings.ToLower(article.Contents)
			query := strings.ToLower(query)
			if strings.Contains(title, query) || strings.Contains(contents, query) {
				articles = append(articles, article)
			}
		}
	}

	return articles
}
