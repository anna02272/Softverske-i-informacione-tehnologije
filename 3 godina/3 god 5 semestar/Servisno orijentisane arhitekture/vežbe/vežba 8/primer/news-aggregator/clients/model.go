package clients

import "soa/2023-2024/lab7/news-aggregator/domain"

type SearchResp struct {
	Articles []Article
}

type Article struct {
	Title    string
	Authors  []string
	Contents string
}

func toDomainArticles(resp SearchResp, source string) []domain.Article {
	articles := make([]domain.Article, len(resp.Articles))
	for i, article := range resp.Articles {
		articles[i] = domain.Article{
			Title:    article.Title,
			Authors:  article.Authors,
			Contents: article.Contents,
			Source:   source,
		}
	}
	return articles
}
