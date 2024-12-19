package application

import (
	"errors"
	"time"

	"github.com/tamararankovic/microservices_demo/ordering_service/client/inventory"
	"github.com/tamararankovic/microservices_demo/ordering_service/client/shipping"
	"github.com/tamararankovic/microservices_demo/ordering_service/domain"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type OrderService struct {
	store           domain.OrderStore
	inventoryClient inventory.Client
	shippingClient  shipping.Client
}

func NewOrderService(store domain.OrderStore, inventoryClient inventory.Client, shippingClient shipping.Client) *OrderService {
	return &OrderService{
		store:           store,
		inventoryClient: inventoryClient,
		shippingClient:  shippingClient,
	}
}

func (service *OrderService) Get(id primitive.ObjectID) (*domain.Order, error) {
	return service.store.Get(id)
}

func (service *OrderService) GetAll() ([]*domain.Order, error) {
	return service.store.GetAll()
}

func (service *OrderService) GetDetails(id primitive.ObjectID) (*domain.OrderDetails, error) {
	order, err := service.store.Get(id)
	if err != nil {
		return nil, err
	}

	shippingInfo, err := service.shippingClient.Get(id.Hex())
	if err != nil {
		return nil, err
	}

	return &domain.OrderDetails{
		Id:              order.Id,
		CreatedAt:       order.CreatedAt,
		Items:           order.Items,
		ShippingAddress: shippingInfo.ShippingAddress,
		ShippingStatus:  shippingInfo.Status,
	}, nil
}

func (service *OrderService) Create(order *domain.Order, address string) error {
	order.Id = primitive.NewObjectID()

	// pokusavamo da zakazemo isporuku u shipping servisu
	err := service.shippingClient.ScheduleOrderShipping(order.Id.Hex(), address)
	// isporuka nije uspesno zakazana
	if err != nil {
		return errors.New("shipping - order could not be created")
	}

	// pokusavamo da izmenimo stanje proizvoda u inventory servisu
	err = service.inventoryClient.UpdateInventory(order)
	// stanje proizvoda nije uspesno izmenjeno
	if err != nil {
		return errors.New("inventory - order could not be created")
	}

	order.CreatedAt = time.Now()
	err = service.store.Insert(order)
	return err
}
