package data

type Order struct {
	Id    int64
	Items []OrderItem
}

type OrderItem struct {
	ProductId int64
	Quantity  uint16
}

var Orders = map[int64]Order{
	1: {
		Id: 1,
		Items: []OrderItem{
			{
				ProductId: 1,
				Quantity:  2,
			},
			{
				ProductId: 2,
				Quantity:  5,
			},
		},
	},
	2: {
		Id: 2,
		Items: []OrderItem{
			{
				ProductId: 1,
				Quantity:  3,
			},
			{
				ProductId: 3,
				Quantity:  2,
			},
		},
	},
}
