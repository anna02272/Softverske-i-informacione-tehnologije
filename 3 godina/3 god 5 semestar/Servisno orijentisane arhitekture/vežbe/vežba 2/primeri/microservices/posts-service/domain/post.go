package domain

import (
	"github.com/google/uuid"
)

type Post struct {
	Id      uuid.UUID
	Owner   User
	Content string
	Likes   []User
}

func (p Post) Of(user User) bool {
	return p.Owner.Equals(user)
}

type PostRepository interface {
	Get(id uuid.UUID) (Post, error)
	GetAll() ([]Post, error)
	GetByUser(user User) ([]Post, error)
	Create(Post Post) (Post, error)
	Update(Post Post) error
}
