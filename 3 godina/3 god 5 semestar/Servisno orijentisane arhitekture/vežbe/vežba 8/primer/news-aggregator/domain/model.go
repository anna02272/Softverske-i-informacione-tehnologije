package domain

import "context"

type Article struct {
	Title    string
	Authors  []string
	Contents string
	Source   string
}

type NewsSource interface {
	Search(ctx context.Context, query string) ([]Article, error)
}
