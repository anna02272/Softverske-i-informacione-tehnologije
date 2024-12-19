package data

import (
	"fmt"

	"github.com/google/uuid"
)

const (
	products = "products/%s"
	all		 = "products"
)

func generateKey() (string, string) {
	id := uuid.New().String()
	return fmt.Sprintf(products, id), id
}

func constructKey(id string) string {
	return fmt.Sprintf(products, id)
}