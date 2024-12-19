package repositories

import (
	"soa/2023-2024/lab1/monolith/domain"

	"github.com/google/uuid"
)

type userInMemRepository struct {
	users map[uuid.UUID]userDao
}

func NewUserInMem() (domain.UserRepository, error) {
	return &userInMemRepository{
		users: make(map[uuid.UUID]userDao),
	}, nil
}

func (u *userInMemRepository) WithData(users map[uuid.UUID]domain.User) (domain.UserRepository, error) {
	if u.users == nil {
		u.users = make(map[uuid.UUID]userDao)
	}
	for _, user := range users {
		u.users[user.Id] = userDao{
			Id:       user.Id,
			Username: user.Username,
			Password: user.Password,
		}
	}
	return u, nil
}

func (u *userInMemRepository) Get(id uuid.UUID) (domain.User, error) {
	user, ok := u.users[id]
	if !ok {
		return domain.User{}, domain.ErrUserNotFound()
	}
	return domain.User{
		Id:       user.Id,
		Username: user.Username,
		Password: user.Password,
	}, nil
}

func (u *userInMemRepository) GetAll() ([]domain.User, error) {
	users := make([]domain.User, 0)
	for _, user := range u.users {
		users = append(users, domain.User{
			Id:       user.Id,
			Username: user.Username,
			Password: user.Password,
		})
	}
	return users, nil
}

func (u *userInMemRepository) Create(user domain.User) (domain.User, error) {
	user.Id = uuid.New()
	u.users[user.Id] = userDao{
		Id:       user.Id,
		Username: user.Username,
		Password: user.Password,
	}
	return user, nil
}

func (u *userInMemRepository) Update(user domain.User) error {
	_, ok := u.users[user.Id]
	if !ok {
		return domain.ErrUserNotFound()
	}
	u.users[user.Id] = userDao{
		Id:       user.Id,
		Username: user.Username,
		Password: user.Password,
	}
	return nil
}

type userDao struct {
	Id       uuid.UUID
	Username string
	Password string
}
