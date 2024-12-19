package domain

import (
	"time"

	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Color struct {
	Code string `bson:"code"`
}

type Product struct {
	Id    string `bson:"_id"`
	Color Color  `bson:"color"`
}

type OrderItem struct {
	Product  Product `bson:"product"`
	Quantity uint16  `bson:"quantity"`
}

type Order struct {
	Id        primitive.ObjectID `bson:"_id"`
	CreatedAt time.Time          `bson:"created_at"`
	Items     []OrderItem        `bson:"items"`
}

type CreateOrderRequest struct {
	Order           Order
	ShippingAddress string
}

type OrderDetails struct {
	Id              primitive.ObjectID
	CreatedAt       time.Time
	Items           []OrderItem
	ShippingAddress string
	ShippingStatus  string
}
