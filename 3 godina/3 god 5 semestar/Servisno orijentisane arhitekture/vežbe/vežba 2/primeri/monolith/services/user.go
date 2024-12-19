package services

import (
	"soa/2023-2024/lab1/monolith/domain"

	"github.com/google/uuid"
)

type UserService struct {
	users domain.UserRepository
}

func NewUserService(users domain.UserRepository) (UserService, error) {
	return UserService{
		users: users,
	}, nil
}

func (s UserService) get(id string) (domain.User, error) {
	uuid, err := uuid.Parse(id)
	if err != nil {
		return domain.User{}, err
	}
	return s.users.Get(uuid)
}

func (s UserService) Create(username, password string) (domain.User, error) {
	user := domain.User{
		Username: username,
		Password: password,
	}
	return s.users.Create(user)
}
