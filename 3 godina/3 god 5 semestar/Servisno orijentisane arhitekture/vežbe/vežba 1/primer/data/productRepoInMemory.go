package data

import (
	"errors"
	"log"
	"strconv"
	"time"
)

type ProductRepoInMemory struct {
	logger *log.Logger
}

func NewInMemory(l *log.Logger) (ProductRepoInMemory, error) {
	return ProductRepoInMemory{l}, nil
}

//Return all the products
func (pr* ProductRepoInMemory) GetAll() Products {
	pr.logger.Println("{ProductRepoInMemory} - getting all products")
	return productList
}

//Return only active products
func (pr* ProductRepoInMemory) GetProducts() Products {
	pr.logger.Println("{ProductRepoInMemory} - getting products")
	list := Products{}

	for _, prod := range productList {
		if len(prod.DeletedOn) == 0 {
			list = append(list, prod)
		}
	}

	return list
}

func (pr* ProductRepoInMemory) AddProduct(p *Product) {
	pr.logger.Println("{ProductRepoInMemory} - getting adding product")
	p.ID = pr.getNextID()
	p.CreatedOn = time.Now().UTC().String()
	p.UpdatedOn = time.Now().UTC().String()
	p.DeletedOn = ""
	productList = append(productList, p)
}

func (pr* ProductRepoInMemory) PutProduct(p *Product, id int) error {
	pr.logger.Println("{ProductRepoInMemory} - getting putting product")
	for _, currentProd := range pr.GetProducts() {
		if currentProd.ID == id && len(currentProd.DeletedOn) == 0 {
			currentProd.Name = p.Name
			currentProd.Description = p.Description
			currentProd.Price = p.Price
			currentProd.SKU = p.SKU
			currentProd.UpdatedOn = time.Now().UTC().String()

			return nil
		}
	}

	return errors.New("Item with id " + strconv.FormatInt(int64(id), 10) + " not found")
}

func (pr* ProductRepoInMemory) DeleteProduct(id int) error {
	pr.logger.Println("{ProductRepoInMemory} - getting deleting product")
	for _, currentProd := range pr.GetProducts() {
		if currentProd.ID == id && len(currentProd.DeletedOn) == 0 {
			currentProd.DeletedOn = time.Now().UTC().String()
			currentProd.UpdatedOn = time.Now().UTC().String()
			return nil
		}
	}
	return errors.New("Item with id " + strconv.FormatInt(int64(id), 10) + " not found")
}

func (pr* ProductRepoInMemory) getNextID() int {
	max := 0

	for _, currentProd := range pr.GetProducts() {
		if currentProd.ID > max {
			max = currentProd.ID
		}
	}

	return max + 1
}

//Our initial database
var productList = []*Product{
	&Product{
		ID:          1,
		Name:        "Latte",
		Description: "Frothy milky coffee",
		Price:       2.45,
		SKU:         "abc323",
		CreatedOn:   time.Now().UTC().String(),
		UpdatedOn:   time.Now().UTC().String(),
	},
	&Product{
		ID:          2,
		Name:        "Esspresso",
		Description: "Short and strong coffee without milk",
		Price:       1.99,
		SKU:         "fjd34",
		CreatedOn:   time.Now().UTC().String(),
		UpdatedOn:   time.Now().UTC().String(),
	},
}
