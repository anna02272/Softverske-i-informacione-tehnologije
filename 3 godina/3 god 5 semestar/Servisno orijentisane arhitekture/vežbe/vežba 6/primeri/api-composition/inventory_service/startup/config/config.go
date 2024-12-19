package config

import "os"

type Config struct {
	Port            string
	InventoryDBHost string
	InventoryDBPort string
	InventoryDBName string
	InventoryDBUser string
	InventoryDBPass string
}

func NewConfig() *Config {
	return &Config{
		Port:            os.Getenv("INVENTORY_SERVICE_PORT"),
		InventoryDBHost: os.Getenv("INVENTORY_DB_HOST"),
		InventoryDBPort: os.Getenv("INVENTORY_DB_PORT"),
		InventoryDBName: os.Getenv("INVENTORY_DB_NAME"),
		InventoryDBUser: os.Getenv("INVENTORY_DB_USER"),
		InventoryDBPass: os.Getenv("INVENTORY_DB_PASS"),
	}
}
