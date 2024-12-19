package repositories

import (
	"fmt"
	"soa/2023-2024/lab1/microservices/connections-service/domain"

	"github.com/google/uuid"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

type connectionPostgresRepository struct {
	db *gorm.DB
}

func NewConnectionPostgres(host, port, username, password, name string) (domain.ConnectionRepository, error) {
	dbUri := fmt.Sprintf("host=%s user=%s dbname=%s password=%s port=%s", host, username, name, password, port)
	db, err := gorm.Open(postgres.Open(dbUri), &gorm.Config{})
	if err != nil {
		return &connectionPostgresRepository{}, err
	}

	setup(db)
	return &connectionPostgresRepository{db: db}, nil
}

func setup(db *gorm.DB) {
	db.AutoMigrate(&connectionDao{})
}

func (r *connectionPostgresRepository) Exists(connection domain.Connection) bool {
	tx := r.db.
		Where("First = ? and Second = ?", connection.Users[0].Id, connection.Users[1].Id).
		Or("First = ? and Second = ?", connection.Users[1].Id, connection.Users[0].Id).First(&connectionDao{})
	return tx.RowsAffected > 0
}

func (r *connectionPostgresRepository) GetByUser(user domain.User) ([]domain.Connection, error) {
	connections := make([]domain.Connection, 0)

	var connectionsDao []*connectionDao
	r.db.Where("First = ? or Second = ?", user.Id, user.Id).Find(&connectionsDao)

	for _, connection := range connectionsDao {
		connections = append(connections, r.fromDao(*connection))
	}
	return connections, nil
}

func (r *connectionPostgresRepository) Create(connection domain.Connection) (domain.Connection, error) {
	if r.Exists(connection) {
		return connection, nil
	}
	connDao := r.toDao(connection)
	r.db.Create(&connDao)
	return connection, nil
}

func (r *connectionPostgresRepository) Delete(connection domain.Connection) error {
	r.db.Delete(r.toDao(connection))
	return nil
}

func (r *connectionPostgresRepository) domainUser(userId uuid.UUID) (domain.User, error) {
	return domain.User{Id: userId}, nil
}

func (r *connectionPostgresRepository) toDao(conn domain.Connection) connectionDao {
	return connectionDao{
		First:  conn.Users[0].Id,
		Second: conn.Users[1].Id,
	}
}

func (r *connectionPostgresRepository) fromDao(connDao connectionDao) domain.Connection {
	first, _ := r.domainUser(connDao.First)
	second, _ := r.domainUser(connDao.Second)
	return domain.Connection{Users: [2]domain.User{first, second}}
}
