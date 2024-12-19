package inventory

type Product struct {
	ProductId string
	ColorCode string
	Quantity  int64
}

type UpdateInventoryRequest struct {
	Products []Product
}
