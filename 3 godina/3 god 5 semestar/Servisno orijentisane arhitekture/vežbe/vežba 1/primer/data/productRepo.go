package data

//Transformed the only implementation we had into an interface so we can standardize the way our code works
//and allow for easy swapping between multiple implementations.
type ProductRepo interface {
	GetAll() Products
	GetProducts() Products
	AddProduct(p *Product)
	PutProduct(p *Product, id int) error
	DeleteProduct(id int) error
}
