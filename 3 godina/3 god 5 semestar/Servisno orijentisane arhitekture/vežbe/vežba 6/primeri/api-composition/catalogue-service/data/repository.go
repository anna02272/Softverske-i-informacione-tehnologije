package data

import (
	"context"
	"fmt"
)

type ProductRepository struct {
	Products map[int64]Product
}

func (r ProductRepository) GetProduct(ctx context.Context, id int64) (Product, error) {
	product, ok := r.Products[id]
	if !ok {
		err := fmt.Errorf("product ID = %d not found", id)
		return Product{}, err
	}
	return product, nil
}
