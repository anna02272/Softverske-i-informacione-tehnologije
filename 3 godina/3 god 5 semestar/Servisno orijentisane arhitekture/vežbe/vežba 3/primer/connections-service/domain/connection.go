package domain

type Connection struct {
	Users [2]User
}

func (c Connection) Of(user User) bool {
	for _, connUser := range c.Users {
		if connUser.Equals(user) {
			return true
		}
	}
	return false
}

func (c Connection) Target(source User) (User, error) {
	if c.Users[0].Equals(source) {
		return c.Users[1], nil
	}
	if c.Users[1].Equals(source) {
		return c.Users[0], nil
	}
	return User{}, errForeignConnection
}

func (c Connection) Equals(conn Connection) bool {
	return (c.Users[0].Equals(conn.Users[0]) && c.Users[1].Equals(conn.Users[1])) ||
		(c.Users[0].Equals(conn.Users[1]) && c.Users[1].Equals(conn.Users[0]))
}

type ConnectionRepository interface {
	GetByUser(user User) ([]Connection, error)
	Exists(connection Connection) bool
	Create(connection Connection) (Connection, error)
	Delete(connection Connection) error
}
