package domain

type Product struct {
	ProductId string `gorm:"index:idx_name,unique"`
	ColorCode string `gorm:"index:idx_name,unique"`
	Quantity  int64
}

type UpdateInventoryRequest struct {
	Products []Product
}
