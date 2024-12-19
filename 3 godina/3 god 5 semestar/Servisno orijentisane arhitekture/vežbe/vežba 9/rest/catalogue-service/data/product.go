package data

type Product struct {
	Id          int64
	Name        string
	Description string
}

var Products = map[int64]Product{
	1: {
		Id:          1,
		Name:        "qqq",
		Description: "qqq",
	},
	2: {
		Id:          2,
		Name:        "qqq",
		Description: "qqq",
	},
	3: {
		Id:          3,
		Name:        "qqq",
		Description: "qqq",
	},
}
