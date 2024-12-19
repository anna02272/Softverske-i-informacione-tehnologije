package repositories

import (
	"soa/2023-2024/lab1/monolith/domain"

	"github.com/google/uuid"
)

type connectionInMemRepository struct {
	connections []connectionDao
	users       domain.UserRepository
}

func NewConnectionInMem(users domain.UserRepository) (domain.ConnectionRepository, error) {
	return &connectionInMemRepository{
		connections: make([]connectionDao, 0),
		users:       users,
	}, nil
}

func (r *connectionInMemRepository) Exists(connection domain.Connection) bool {
	for _, conn := range r.connections {
		user1, err := r.domainUser(conn.Users[0])
		if err != nil {
			continue
		}
		user2, err := r.domainUser(conn.Users[1])
		if err != nil {
			continue
		}
		domainConn := domain.Connection{Users: [2]domain.User{user1, user2}}
		if domainConn.Equals(connection) {
			return true
		}
	}
	return false
}

func (r *connectionInMemRepository) GetByUser(user domain.User) ([]domain.Connection, error) {
	conns := make([]domain.Connection, 0)
	for _, conn := range r.connections {
		user1, err := r.domainUser(conn.Users[0])
		if err != nil {
			continue
		}
		user2, err := r.domainUser(conn.Users[1])
		if err != nil {
			continue
		}
		domainConn := domain.Connection{Users: [2]domain.User{user1, user2}}
		if domainConn.Of(user) {
			conns = append(conns, domainConn)
		}
	}
	return conns, nil
}

func (r *connectionInMemRepository) Create(connection domain.Connection) (domain.Connection, error) {
	if r.Exists(connection) {
		return connection, nil
	}
	r.connections = append(r.connections, connectionDao{
		Users: [2]uuid.UUID{connection.Users[0].Id, connection.Users[1].Id},
	})
	return connection, nil
}

func (r *connectionInMemRepository) Delete(connection domain.Connection) error {
	index := -1
	for i, conn := range r.connections {
		user1, err := r.domainUser(conn.Users[0])
		if err != nil {
			continue
		}
		user2, err := r.domainUser(conn.Users[1])
		if err != nil {
			continue
		}
		domainConn := domain.Connection{Users: [2]domain.User{user1, user2}}
		if domainConn.Equals(connection) {
			index = i
			break
		}
	}
	if index >= 0 {
		r.connections = append(r.connections[:index], r.connections[index+1:]...)
	}
	return nil
}

func (r *connectionInMemRepository) domainUser(userId uuid.UUID) (domain.User, error) {
	return r.users.Get(userId)
}

type connectionDao struct {
	Users [2]uuid.UUID
}
