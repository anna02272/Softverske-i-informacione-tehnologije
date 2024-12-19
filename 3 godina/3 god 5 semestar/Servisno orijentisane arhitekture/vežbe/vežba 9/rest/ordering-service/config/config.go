package config

import "os"

type Config struct {
	Address                 string
	JaegerAddress           string
	CatalogueServiceAddress string
}

func GetConfig() Config {
	return Config{
		Address:                 os.Getenv("ORDERING_SERVICE_ADDRESS"),
		JaegerAddress:           os.Getenv("JAEGER_ADDRESS"),
		CatalogueServiceAddress: os.Getenv("CATALOGUE_SERVICE_ADDRESS"),
	}
}
