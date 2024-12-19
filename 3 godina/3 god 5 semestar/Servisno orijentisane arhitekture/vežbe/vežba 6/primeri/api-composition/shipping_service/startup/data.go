package startup

import (
	"github.com/tamararankovic/microservices_demo/shipping_service/domain"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

var orders = []*domain.Order{
	{
		Id:              getObjectId("6360ed69e504b6e93f964228"),
		Status:          domain.Scheduled,
		ShippingAddress: "Pere Mikica 23, Novi Sad",
	},
}

func getObjectId(id string) primitive.ObjectID {
	if objectId, err := primitive.ObjectIDFromHex(id); err == nil {
		return objectId
	}
	return primitive.NewObjectID()
}
