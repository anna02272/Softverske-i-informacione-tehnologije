package nats

import (
	"example/common/messaging"
	"github.com/nats-io/nats.go"
)

type Publisher struct {
	conn    *nats.EncodedConn
	subject string
}

func NewNATSPublisher(conn *nats.Conn, subject string) (messaging.Publisher, error) {
	encConn, err := nats.NewEncodedConn(conn, nats.JSON_ENCODER)
	if err != nil {
		return nil, err
	}
	return &Publisher{
		conn:    encConn,
		subject: subject,
	}, nil
}

func (p *Publisher) Publish(event messaging.Event) error {
	err := p.conn.Publish(p.subject, event)
	if err != nil {
		return err
	}
	return nil
}
