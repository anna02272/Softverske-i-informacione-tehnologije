package startup

import (
	"time"

	"github.com/tamararankovic/microservices_demo/ordering_service/domain"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

var orders = []*domain.Order{
	{
		Id:        getObjectId("6360ed69e504b6e93f964228"),
		CreatedAt: time.Now(),
		Items: []domain.OrderItem{
			{
				Product: domain.Product{
					Id:    "1",
					Color: domain.Color{Code: "R"},
				},
				Quantity: 5,
			},
			{
				Product: domain.Product{
					Id:    "2",
					Color: domain.Color{Code: "G"},
				},
				Quantity: 3,
			},
		},
	},
}

func getObjectId(id string) primitive.ObjectID {
	if objectId, err := primitive.ObjectIDFromHex(id); err == nil {
		return objectId
	}
	return primitive.NewObjectID()
}
