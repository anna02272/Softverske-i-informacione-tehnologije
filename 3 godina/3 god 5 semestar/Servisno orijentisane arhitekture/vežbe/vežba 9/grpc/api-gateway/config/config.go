package config

import "os"

type Config struct {
	Address               string
	GreeterServiceAddress string
	JaegerAddress         string
}

func GetConfig() Config {
	return Config{
		GreeterServiceAddress: os.Getenv("GREETER_SERVICE_ADDRESS"),
		Address:               os.Getenv("GATEWAY_ADDRESS"),
		JaegerAddress:         os.Getenv("JAEGER_ADDRESS"),
	}
}
