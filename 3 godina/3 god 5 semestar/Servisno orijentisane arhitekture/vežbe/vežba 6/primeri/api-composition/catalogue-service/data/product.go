package data

type Product struct {
	Id          int64
	Name        string
	Description string
	Color       Color
}

type Color struct {
	Code string
	Name string
}

var Products = map[int64]Product{
	1: {
		Id:          1,
		Name:        "qqq",
		Description: "qqq",
		Color: Color{
			Code: "R",
			Name: "red",
		},
	},
	2: {
		Id:          2,
		Name:        "qqq",
		Description: "qqq",
		Color: Color{
			Code: "G",
			Name: "green",
		},
	},
	3: {
		Id:          3,
		Name:        "qqq",
		Description: "qqq",
		Color: Color{
			Code: "R",
			Name: "red",
		},
	},
}
