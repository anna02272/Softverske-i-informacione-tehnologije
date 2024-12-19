package services

import (
	"context"
	"soa/2023-2024/lab1/monolith/domain"
)

type ConnectionService struct {
	conns domain.ConnectionRepository
	users UserService
}

func NewConnectionService(conns domain.ConnectionRepository, users UserService) (ConnectionService, error) {
	return ConnectionService{
		conns: conns,
		users: users,
	}, nil
}

func (s ConnectionService) GetByUser(ctx context.Context, userId string) ([]domain.User, error) {
	authAny := ctx.Value("auth")
	if authAny == nil {
		return nil, domain.ErrUnauthorized()
	}
	authenticated := authAny.(*domain.User)
	if authenticated == nil {
		return nil, domain.ErrUnauthorized()
	}
	user, err := s.users.get(userId)
	if err != nil {
		return nil, err
	}
	if !authenticated.Equals(user) && !s.connected(*authenticated, user) {
		return nil, domain.ErrUnauthorized()
	}
	conns, err := s.conns.GetByUser(user)
	if err != nil {
		return nil, err
	}
	users := make([]domain.User, 0)
	for _, conn := range conns {
		target, err := conn.Target(user)
		if err != nil {
			continue
		}
		users = append(users, target)
	}
	return users, nil
}

func (s ConnectionService) Create(ctx context.Context, sourceUserId, targetUserId string) (domain.Connection, error) {
	authAny := ctx.Value("auth")
	if authAny == nil {
		return domain.Connection{}, domain.ErrUnauthorized()
	}
	authenticated := authAny.(*domain.User)
	if authenticated == nil {
		return domain.Connection{}, domain.ErrUnauthorized()
	}
	sourceUser, err := s.users.get(sourceUserId)
	if err != nil {
		return domain.Connection{}, err
	}
	targetUser, err := s.users.get(targetUserId)
	if err != nil {
		return domain.Connection{}, err
	}
	if !authenticated.Equals(sourceUser) {
		return domain.Connection{}, domain.ErrUnauthorized()
	}
	conn := domain.Connection{
		Users: [2]domain.User{sourceUser, targetUser},
	}
	return s.conns.Create(conn)
}

func (s ConnectionService) Delete(ctx context.Context, sourceUserId, targetUserId string) error {
	authAny := ctx.Value("auth")
	if authAny == nil {
		return domain.ErrUnauthorized()
	}
	authenticated := authAny.(*domain.User)
	if authenticated == nil {
		return domain.ErrUnauthorized()
	}
	sourceUser, err := s.users.get(sourceUserId)
	if err != nil {
		return err
	}
	targetUser, err := s.users.get(targetUserId)
	if err != nil {
		return err
	}
	if !authenticated.Equals(sourceUser) {
		return domain.ErrUnauthorized()
	}
	conn := domain.Connection{
		Users: [2]domain.User{sourceUser, targetUser},
	}
	return s.conns.Delete(conn)
}

func (s ConnectionService) connected(user1, user2 domain.User) bool {
	return s.conns.Exists(domain.Connection{Users: [2]domain.User{user1, user2}})
}
