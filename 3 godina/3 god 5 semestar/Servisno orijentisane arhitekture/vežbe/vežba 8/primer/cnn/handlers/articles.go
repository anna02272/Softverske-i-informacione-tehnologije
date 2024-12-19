package handlers

import (
	"log"
	"net/http"
	"soa/2023-2024/lab7/cnn/domain"
	"strings"
	"time"
)

type ArticleHandler struct {
	articles []domain.Article
	counter  int8
}

func NewArticleHandler(articles []domain.Article) *ArticleHandler {
	return &ArticleHandler{
		articles: articles,
		counter:  0,
	}
}

func (h *ArticleHandler) Search(w http.ResponseWriter, r *http.Request) {
	// we simulate our service being unavailable 20% of the time
	h.counter++
	if h.counter%5 == 0 {
		log.Println("service currently unavailable")
		writeResp(nil, http.StatusServiceUnavailable, w)
		return
	}

	query := r.URL.Query().Get("query")
	articles := h.search(query)
	resp := SearchResp{
		Articles: articles,
	}
	writeResp(resp, http.StatusOK, w)
}

func (h *ArticleHandler) search(query string) []domain.Article {
	articles := make([]domain.Article, 0)
	for _, article := range h.articles {
		log.Println("CNN - processing article: " + article.Title)
		// we simulate the time needed to retrieve and search the text
		time.Sleep(500 * time.Millisecond)
		title := strings.ToLower(article.Title)
		contents := strings.ToLower(article.Contents)
		query := strings.ToLower(query)
		if strings.Contains(title, query) || strings.Contains(contents, query) {
			articles = append(articles, article)
		}
	}
	return articles
}
