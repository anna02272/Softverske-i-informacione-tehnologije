package config

import "os"

type Config struct {
	Port           string
	OrderingDBHost string
	OrderingDBPort string
	InventoryHost  string
	InventoryPort  string
	ShippingHost   string
	ShippingPort   string
}

func NewConfig() *Config {
	return &Config{
		Port:           os.Getenv("ORDERING_SERVICE_PORT"),
		OrderingDBHost: os.Getenv("ORDERING_DB_HOST"),
		OrderingDBPort: os.Getenv("ORDERING_DB_PORT"),
		InventoryHost:  os.Getenv("INVENTORY_SERVICE_HOST"),
		InventoryPort:  os.Getenv("INVENTORY_SERVICE_PORT"),
		ShippingHost:   os.Getenv("SHIPPING_SERVICE_HOST"),
		ShippingPort:   os.Getenv("SHIPPING_SERVICE_PORT"),
	}
}
