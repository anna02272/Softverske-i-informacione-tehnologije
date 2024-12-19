package handlers

import "soa/2023-2024/lab7/news-aggregator/domain"

type SearchResp struct {
	Total    int
	Articles []domain.Article
}
