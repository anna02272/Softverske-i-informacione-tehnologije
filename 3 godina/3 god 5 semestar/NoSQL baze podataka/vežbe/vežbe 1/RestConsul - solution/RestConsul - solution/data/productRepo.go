package data

import (
	"encoding/json"
	"fmt"
	"log"
	"os"
	"time"

	// NoSQL: module containing Consul api client (written in Golang)
	"github.com/hashicorp/consul/api"
)

//In this file we localize all the operations on our database (currently in-memory database with productList)
//When we decide to migrate this to a genuine solution we would have to make changes just to this file

// NoSQL: All changes needed for using Consul as our database are implemented here

// NoSQL: ProductRepo struct encapsulating Consul api client
type ProductRepo struct {
	cli *api.Client
	logger *log.Logger
}

// NoSQL: Constructor which reads db configuration from environment
func New(logger *log.Logger) (*ProductRepo, error) {
	db := os.Getenv("DB")
	dbport := os.Getenv("DBPORT")

	config := api.DefaultConfig()
	config.Address = fmt.Sprintf("%s:%s", db, dbport)
	client, err := api.NewClient(config)
	if err != nil {
		return nil, err
	}

	return &ProductRepo{
		cli: client,
		logger: logger,
	}, nil
}

// NoSQL: Returns all products
func (pr *ProductRepo) GetAll() (Products, error) {
	kv := pr.cli.KV()
	data, _, err := kv.List(all, nil)
	if err != nil {
		return nil, err
	}

	products := Products{}
	for _, pair := range data {
		product := &Product{}
		err = json.Unmarshal(pair.Value, product)
		if err != nil {
			return nil, err
		}
		products = append(products, product)
	}

	return products, nil
}

// NoSQL: Returns Product by id 
func (pr *ProductRepo) Get(id string) (*Product, error) {
	kv := pr.cli.KV()

	pair, _, err := kv.Get(constructKey(id), nil)
	if err != nil {
		return nil, err
	}
	// If pair is nil -> no object found for given id -> return nil
	if pair == nil {
		return nil, nil
	}

	product := &Product{}
	err = json.Unmarshal(pair.Value, product)
	if err != nil {
		return nil, err
	}

	return product, nil
}

// NoSQL: Saves Product to DB
func (pr *ProductRepo) Post(product *Product) (*Product, error) {
	kv := pr.cli.KV()
	
	product.CreatedOn = time.Now().UTC().String()
	product.UpdatedOn = time.Now().UTC().String()
	product.DeletedOn = ""
	
	dbId, id := generateKey()
	product.ID = id
	
	data, err := json.Marshal(product)
	if err != nil {
		return nil, err
	}
	
	productKeyValue := &api.KVPair{Key: dbId, Value: data}
	_, err = kv.Put(productKeyValue, nil)
	if err != nil {
		return nil, err
	}
	
	return product, nil
}

// NoSQL: TODO - updates Product
func (pr *ProductRepo) Put(id string, product *Product) (*Product, error) {
	kv := pr.cli.KV()

	product.UpdatedOn = time.Now().UTC().String()

	data, err := json.Marshal(product)
	if err != nil {
		return nil, err
	}

	productKeyValue := &api.KVPair{Key: constructKey(id), Value: data}
	_, err = kv.Put(productKeyValue, nil)
	if err != nil {
		return nil, err
	}

	return product, nil
}

// NoSQL: Delete Product by id
func (pr *ProductRepo) Delete(id string) (error) {
	kv := pr.cli.KV()
	
	_, err := kv.Delete(constructKey(id), nil)
	if err != nil {
		return err
	}

	return nil
}

// NoSQL: TODO - delete all products
func (pr *ProductRepo) DeleteAll() (error) {
	kv := pr.cli.KV()

	_, err := kv.DeleteTree(all, nil)
	if err != nil {
		return err
	}

	return nil
}

// NoSQL: Bonus TODO - create Product with type information
func (pr *ProductRepo) PostByType(product *Product) (*Product, error) {
	kv := pr.cli.KV()
	
	product.CreatedOn = time.Now().UTC().String()
	product.UpdatedOn = time.Now().UTC().String()
	product.DeletedOn = ""
	
	dbId, id := generateTypeKey(product.Type)
	product.ID = id
	
	data, err := json.Marshal(product)
	if err != nil {
		return nil, err
	}
	
	productKeyValue := &api.KVPair{Key: dbId, Value: data}
	_, err = kv.Put(productKeyValue, nil)
	if err != nil {
		return nil, err
	}
	
	return product, nil
}

// NoSQL: Bonus TODO - get all Products by type
func (pr *ProductRepo) GetByType(productType string) (Products, error) {
	kv := pr.cli.KV()

	data, _, err := kv.List(constructTypeKey(productType, ""), nil)
	if err != nil {
		return nil, err
	}

	products := Products{}
	for _, pair := range data {
		product := &Product{}
		err = json.Unmarshal(pair.Value, product)
		if err != nil {
			return nil, err
		}
		products = append(products, product)
	}

	return products, nil
}
