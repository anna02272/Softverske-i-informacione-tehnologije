package cache

import (
	"Rest/data"
	"encoding/json"
	"fmt"
	"log"
	"os"
	"time"

	"github.com/go-redis/redis"
)

type ProductCache struct {
	cli *redis.Client
	logger *log.Logger
}

// Construct Redis client
func New(logger *log.Logger) (*ProductCache) {
	redisHost := os.Getenv("REDIS_HOST")
	redisPort := os.Getenv("REDIS_PORT")
	redisAddress := fmt.Sprintf("%s:%s", redisHost, redisPort)
	
	client := redis.NewClient(&redis.Options{
		Addr: redisAddress,
	})

	return &ProductCache{
		cli: client,
		logger: logger,
	}
}

// Check connection function
func (pc *ProductCache) Ping() {
	val, _ := pc.cli.Ping().Result()
	pc.logger.Println(val)
}

// Set key-value pair with default expiration
func (pc *ProductCache) Post(product *data.Product) error {
	key := product.ID
	value, err := json.Marshal(product)
	if err != nil {
		return err
	}
	
	err = pc.cli.Set(constructKey(key), value, 30*time.Second).Err()

	return err
}

// Get value by key
func (pc *ProductCache) Get(id string) (*data.Product, error) {
	value, err := pc.cli.Get(constructKey(id)).Bytes()
	if err != nil {
		return nil, err
	}
	
	product := &data.Product{}
	err = json.Unmarshal(value, product)
	if err != nil {
		return nil, err
	}
	
	pc.logger.Println("Cache hit")
	return product, nil
}

// Check if given key exists
func (pc *ProductCache) Exists(id string) bool {
	cnt, err := pc.cli.Exists(constructKey(id)).Result()
	if cnt == 1 {
		return true
	}
	if err != nil {
		return false
	}
	return false
}
