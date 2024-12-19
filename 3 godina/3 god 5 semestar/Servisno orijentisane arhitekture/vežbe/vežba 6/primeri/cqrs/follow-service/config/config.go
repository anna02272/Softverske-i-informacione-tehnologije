package config

import "os"

type Config struct {
	Port        string
	NatsHost    string
	NatsPort    string
	NatsUser    string
	NatsPass    string
	NatsSubject string
}

func NewConfig() *Config {
	return &Config{
		Port:        os.Getenv("PORT"),
		NatsHost:    os.Getenv("NATS_HOST"),
		NatsPort:    os.Getenv("NATS_PORT"),
		NatsUser:    os.Getenv("NATS_USER"),
		NatsPass:    os.Getenv("NATS_PASS"),
		NatsSubject: os.Getenv("NATS_SUBJECT"),
	}
}
