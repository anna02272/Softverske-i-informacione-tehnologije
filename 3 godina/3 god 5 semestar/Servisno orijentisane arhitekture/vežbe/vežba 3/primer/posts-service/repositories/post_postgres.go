package repositories

import (
	"database/sql/driver"
	"errors"
	"fmt"
	"soa/2023-2024/lab1/microservices/posts-service/domain"
	"strings"

	"github.com/google/uuid"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

type postPostgresRepository struct {
	db *gorm.DB
}

func NewPostPostgres(host, port, username, password, name string) (domain.PostRepository, error) {
	dbUri := fmt.Sprintf("host=%s user=%s dbname=%s password=%s port=%s", host, username, name, password, port)
	db, err := gorm.Open(postgres.Open(dbUri), &gorm.Config{})
	if err != nil {
		return &postPostgresRepository{}, err
	}

	setup(db)
	return &postPostgresRepository{db: db}, nil
}

func setup(db *gorm.DB) {
	db.AutoMigrate(&postDao{})
}

func (r *postPostgresRepository) Get(id uuid.UUID) (domain.Post, error) {
	var post postDao
	tx := r.db.Where("Id = ?", id).First(&post)
	if tx.RowsAffected == 0 {
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

func (r *postPostgresRepository) GetAll() ([]domain.Post, error) {
	posts := make([]domain.Post, 0)

	var postsDao []*postDao
	r.db.Find(&postsDao)

	for _, post := range postsDao {
		owner, err := r.domainUser(post.Owner)
		if err != nil {
			continue
		}
		likes := make([]domain.User, 0)
		for _, like := range post.Likes {
			user, _ := r.domainUser(like)
			likes = append(likes, user)
		}
		posts = append(posts, domain.Post{
			Id:      post.Id,
			Content: post.Content,
			Owner:   owner,
			Likes:   likes,
		})
	}
	return posts, nil
}

func (r *postPostgresRepository) GetByUser(user domain.User) ([]domain.Post, error) {
	posts := make([]domain.Post, 0)

	var postsDao []*postDao
	r.db.Where("Owner = ?", user.Id).Find(&postsDao)

	for _, post := range postsDao {
		owner, err := r.domainUser(post.Owner)
		if err != nil {
			continue
		}
		likes := make([]domain.User, 0)
		for _, like := range post.Likes {
			user, _ := r.domainUser(like)
			likes = append(likes, user)
		}
		posts = append(posts, domain.Post{
			Id:      post.Id,
			Content: post.Content,
			Owner:   owner,
			Likes:   likes,
		})
	}
	return posts, nil
}

func (r *postPostgresRepository) Create(post domain.Post) (domain.Post, error) {
	post.Id = uuid.New()
	postDao := &postDao{
		Id:      post.Id,
		Content: post.Content,
		Owner:   post.Owner.Id,
	}
	for _, like := range post.Likes {
		postDao.Likes = append(postDao.Likes, like.Id)
	}
	r.db.Create(&postDao)
	return post, nil
}

func (r *postPostgresRepository) Update(post domain.Post) error {
	postDao := postDao{
		Id:      post.Id,
		Content: post.Content,
		Owner:   post.Owner.Id,
	}
	for _, like := range post.Likes {
		postDao.Likes = append(postDao.Likes, like.Id)
	}
	r.db.Save(&postDao)
	return nil
}

func (r *postPostgresRepository) domainUser(userId uuid.UUID) (domain.User, error) {
	return domain.User{Id: userId}, nil
}

type postDao struct {
	Id      uuid.UUID
	Owner   uuid.UUID
	Content string
	Likes   likes `gorm:"type:VARCHAR(2000)"`
}

type likes []uuid.UUID

func (l *likes) Scan(src any) error {
	val, ok := src.(string)
	if !ok {
		return errors.New("src value cannot cast to []byte")
	}
	ids := strings.Split(val, ",")
	for _, id := range ids {
		uuid, err := uuid.Parse(id)
		if err != nil {
			continue
		}
		*l = append(*l, uuid)
	}
	return nil
}

func (l likes) Value() (driver.Value, error) {
	if len(l) == 0 {
		return nil, nil
	}
	val := ""
	for _, like := range l {
		val += "," + like.String()
	}
	return val[1:], nil
}
