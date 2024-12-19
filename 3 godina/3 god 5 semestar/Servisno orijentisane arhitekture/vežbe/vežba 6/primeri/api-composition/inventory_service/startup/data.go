package startup

import "github.com/tamararankovic/microservices_demo/inventory_service/domain"

var products = []*domain.Product{
	{
		ProductId: "1",
		ColorCode: "R",
		Quantity:  10,
	},
	{
		ProductId: "2",
		ColorCode: "G",
		Quantity:  12,
	},
	{
		ProductId: "3",
		ColorCode: "R",
		Quantity:  3,
	},
}
