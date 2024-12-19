package cache

import (
	"fmt"
)

const (
	cacheProducts = "products:%s"
	cacheAll	  = "products"
	freshProducts = "products:fresh"
)

func constructKey(id string) string {
	return fmt.Sprintf(cacheProducts, id)
}
