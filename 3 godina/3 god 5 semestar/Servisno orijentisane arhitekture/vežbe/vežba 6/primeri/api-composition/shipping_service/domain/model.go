package domain

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type OrderStatus int8

const (
	Scheduled OrderStatus = iota
	InTransport
	Delivered
	Cancelled
)

func (status OrderStatus) String() string {
	switch status {
	case Scheduled:
		return "Scheduled"
	case InTransport:
		return "In transport"
	case Delivered:
		return "Delivered"
	case Cancelled:
		return "Cancelled"
	}
	return "Unknown"
}

type Order struct {
	Id              primitive.ObjectID `bson:"_id"`
	Status          OrderStatus        `bson:"status"`
	ShippingAddress string             `bson:"shipping_address"`
}

type ScheduleOrderRequest struct {
	Id              string
	ShippingAddress string
}

type GetOrderShippingInfoResponse struct {
	Id              string
	ShippingAddress string
	Status          string
}
