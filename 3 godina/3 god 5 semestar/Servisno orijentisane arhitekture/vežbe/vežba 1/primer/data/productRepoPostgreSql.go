package data

import (
	"errors"
	"fmt"
	"log"
	"os"
	"time"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

//New implementation that uses postgres, it implements the ProductRepo
type ProductRepoPostgreSql struct {
	log *log.Logger
	db *gorm.DB
}

func NewPostgreSql(log *log.Logger) (ProductRepoPostgreSql, error) {
	username := os.Getenv("db_username")
	host := os.Getenv("db_host")
	password := os.Getenv("db_password")
	name := os.Getenv("db_name")
	port := os.Getenv("db_port")

	dbUri := fmt.Sprintf("host=%s user=%s dbname=%s password=%s port=%s", host, username, name, password, port);

	db, err := gorm.Open(postgres.Open(dbUri), &gorm.Config{})

	if err != nil {
		return ProductRepoPostgreSql{}, err
	}

	setup(db);
	return ProductRepoPostgreSql{log, db}, nil
}

func setup(db *gorm.DB) {
	db.AutoMigrate(&Product{})
}

func (pr* ProductRepoPostgreSql) GetAll() Products {
	pr.log.Println("{ProductRepoPostgreSql} - getting all products");
	var products []*Product;

	pr.db.Find(&products);

	return products
}

func (pr* ProductRepoPostgreSql) GetProducts() Products {
	pr.log.Println("{ProductRepoPostgreSql} - getting products");
	var products []*Product;

	pr.db.Where("deleted_on = ?", "").Find(&products);

	return products;
}

func (pr* ProductRepoPostgreSql) AddProduct(p* Product) {
	pr.log.Println("{ProductRepoPostgreSql} - posting product");

	p.CreatedOn = time.Now().UTC().String();
	p.UpdatedOn = time.Now().UTC().String();
	p.DeletedOn = "";

	pr.db.Create(p);
}

func (pr* ProductRepoPostgreSql) PutProduct(p *Product, id int) error {
	pr.log.Println("{ProductRepoPostgreSql} - putting product");

	var product Product;

	pr.db.Where("id = ? AND deleted_on = ?", id, "").Find(&product);

	if product.ID == 0 {
		return errors.New(fmt.Sprintf("Product with id %d not found", id));
	}

	product.UpdatedOn = time.Now().UTC().String();
	product.Name = p.Name;
	product.Description = p.Description;
	product.Price = p.Price;
	product.SKU = p.SKU;

	pr.db.Save(&product);

	*p = product;
	return nil;
}

func (pr* ProductRepoPostgreSql) DeleteProduct(id int) error {
	pr.log.Println("{ProductRepoPostgreSql} - deleting product");

	var product Product;

	pr.db.Where("id = ? AND deleted_on = ?", id, "").Find(&product);

	if product.ID == 0 {
		return errors.New(fmt.Sprintf("Product with id %d not found", id));
	}

	product.DeletedOn = time.Now().UTC().String();

	pr.db.Save(&product);
	return nil;
}