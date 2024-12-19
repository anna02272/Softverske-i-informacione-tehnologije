package repositories

import (
	"soa/2023-2024/lab1/microservices/posts-service/domain"

	"github.com/google/uuid"
)

type postInMemRepository struct {
	posts map[uuid.UUID]postDao
}

func NewPostInMem() (domain.PostRepository, error) {
	return &postInMemRepository{
		posts: make(map[uuid.UUID]postDao),
	}, nil
}

func (r *postInMemRepository) Get(id uuid.UUID) (domain.Post, error) {
	post, ok := r.posts[id]
	if !ok {
		return domain.Post{}, domain.ErrPostNotFound()
	}
	owner, err := r.domainUser(post.Owner)
	if err != nil {
		return domain.Post{}, domain.ErrUserNotFound()
	}
	return domain.Post{
		Id:      post.Id,
		Content: post.Content,
		Owner:   owner,
	}, nil
}

func (r *postInMemRepository) GetAll() ([]domain.Post, error) {
	posts := make([]domain.Post, 0)
	for _, post := range r.posts {
		owner, err := r.domainUser(post.Owner)
		if err != nil {
			continue
		}
		posts = append(posts, domain.Post{
			Id:      post.Id,
			Content: post.Content,
			Owner:   owner,
		})
	}
	return posts, nil
}

func (r *postInMemRepository) GetByUser(user domain.User) ([]domain.Post, error) {
	posts := make([]domain.Post, 0)
	for _, post := range r.posts {
		owner, err := r.domainUser(post.Owner)
		if err != nil {
			continue
		}
		domainPost := domain.Post{
			Id:      post.Id,
			Content: post.Content,
			Owner:   owner,
		}
		if domainPost.Of(user) {
			for _, like := range post.Likes {
				domainLike, err := r.domainUser(like)
				if err != nil {
					continue
				}
				domainPost.Likes = append(domainPost.Likes, domainLike)
			}
			posts = append(posts, domainPost)
		}
	}
	return posts, nil
}

func (r *postInMemRepository) Create(post domain.Post) (domain.Post, error) {
	post.Id = uuid.New()
	postDao := postDao{
		Id:      post.Id,
		Content: post.Content,
		Owner:   post.Owner.Id,
	}
	for _, like := range post.Likes {
		postDao.Likes = append(postDao.Likes, like.Id)
	}
	r.posts[post.Id] = postDao
	return post, nil
}

func (r *postInMemRepository) Update(post domain.Post) error {
	_, ok := r.posts[post.Id]
	if !ok {
		return domain.ErrPostNotFound()
	}
	postDao := postDao{
		Id:      post.Id,
		Content: post.Content,
		Owner:   post.Owner.Id,
	}
	for _, like := range post.Likes {
		postDao.Likes = append(postDao.Likes, like.Id)
	}
	r.posts[post.Id] = postDao
	return nil
}

func (r *postInMemRepository) domainUser(userId uuid.UUID) (domain.User, error) {
	return domain.User{Id: userId}, nil
}

type postDao struct {
	Id      uuid.UUID
	Owner   uuid.UUID
	Content string
	Likes   []uuid.UUID
}
