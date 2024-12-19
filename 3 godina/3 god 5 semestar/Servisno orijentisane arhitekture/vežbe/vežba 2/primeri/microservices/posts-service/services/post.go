package services

import (
	"context"
	"soa/2023-2024/lab1/microservices/posts-service/domain"

	"github.com/google/uuid"
)

type PostService struct {
	posts domain.PostRepository
	conns ConnectionService
}

func NewPostService(posts domain.PostRepository, conns ConnectionService) (PostService, error) {
	return PostService{
		posts: posts,
		conns: conns,
	}, nil
}

func (s PostService) get(id string) (domain.Post, error) {
	uuid, err := uuid.Parse(id)
	if err != nil {
		return domain.Post{}, err
	}
	return s.posts.Get(uuid)
}

func (s PostService) Create(ctx context.Context, ownerId, content string) (domain.Post, error) {
	authAny := ctx.Value("auth")
	if authAny == nil {
		return domain.Post{}, domain.ErrUnauthorized()
	}
	authenticated := authAny.(*domain.User)
	if authenticated == nil {
		return domain.Post{}, domain.ErrUnauthorized()
	}
	ownerUuid, err := uuid.Parse(ownerId)
	if err != nil {
		return domain.Post{}, domain.ErrUnauthorized()
	}
	owner := domain.User{Id: ownerUuid}
	if !owner.Equals(*authenticated) {
		return domain.Post{}, domain.ErrUnauthorized()
	}
	post := domain.Post{
		Owner:   owner,
		Content: content,
		Likes:   make([]domain.User, 0),
	}
	return s.posts.Create(post)
}

func (s PostService) Like(ctx context.Context, postId, userId string) (domain.Post, error) {
	authAny := ctx.Value("auth")
	if authAny == nil {
		return domain.Post{}, domain.ErrUnauthorized()
	}
	authenticated := authAny.(*domain.User)
	if authenticated == nil {
		return domain.Post{}, domain.ErrUnauthorized()
	}
	userUuid, err := uuid.Parse(userId)
	if err != nil {
		return domain.Post{}, domain.ErrUnauthorized()
	}
	user := domain.User{Id: userUuid}
	post, err := s.get(postId)
	if err != nil {
		return domain.Post{}, domain.ErrPostNotFound()
	}
	if !s.conns.connected(ctx, [2]domain.User{post.Owner, user}) {
		return domain.Post{}, domain.ErrUnauthorized()
	}
	post.Likes = append(post.Likes, user)
	return post, s.posts.Update(post)
}

func (s PostService) GetHomeFeed(ctx context.Context, userId string) ([]domain.Post, error) {
	authAny := ctx.Value("auth")
	if authAny == nil {
		return nil, domain.ErrUnauthorized()
	}
	authenticated := authAny.(*domain.User)
	if authenticated == nil {
		return nil, domain.ErrUnauthorized()
	}
	userUuid, err := uuid.Parse(userId)
	if err != nil {
		return nil, domain.ErrUnauthorized()
	}
	user := domain.User{Id: userUuid}
	conns, err := s.conns.getByUser(ctx, user)
	if err != nil {
		return nil, err
	}
	posts := make([]domain.Post, 0)
	for _, conn := range conns {
		connPosts, err := s.posts.GetByUser(conn)
		if err != nil {
			continue
		}
		posts = append(posts, connPosts...)
	}
	return posts, nil
}
