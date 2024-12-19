package handlers

import (
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/tamararankovic/microservices_demo/shipping_service/application"
	"github.com/tamararankovic/microservices_demo/shipping_service/domain"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type OrderHandler struct {
	service *application.OrderService
}

func NewOrderHandler(service *application.OrderService) *OrderHandler {
	return &OrderHandler{
		service: service,
	}
}

func (handler *OrderHandler) Init(r *mux.Router) {
	r.HandleFunc("/", handler.GetAll).Methods("GET")
	r.HandleFunc("/{id}", handler.Get).Methods("GET")
	r.HandleFunc("/", handler.ScheduleOrderShipping).Methods("POST")
	r.HandleFunc("/", handler.CancelOrderShipping).Methods("DELETE")
	http.Handle("/", r)
}

func (handler *OrderHandler) Get(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, ok := vars["id"]
	if !ok {
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	objectId, err := primitive.ObjectIDFromHex(id)
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	order, err := handler.service.Get(objectId)
	if err != nil {
		w.WriteHeader(http.StatusNotFound)
		return
	}

	resp := domain.GetOrderShippingInfoResponse{
		Id:              order.Id.Hex(),
		ShippingAddress: order.ShippingAddress,
		Status:          order.Status.String(),
	}
	jsonResponse(resp, w)
}

func (handler *OrderHandler) GetAll(w http.ResponseWriter, r *http.Request) {
	orders, err := handler.service.GetAll()
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		return
	}
	jsonResponse(orders, w)
}

func (handler *OrderHandler) ScheduleOrderShipping(w http.ResponseWriter, r *http.Request) {
	var req domain.ScheduleOrderRequest
	err := json.NewDecoder(r.Body).Decode(&req)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	orderId, err := primitive.ObjectIDFromHex(req.Id)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	order := &domain.Order{
		Id:              orderId,
		ShippingAddress: req.ShippingAddress,
	}
	err = handler.service.Create(order)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	w.WriteHeader(http.StatusOK)
}

func (handler *OrderHandler) CancelOrderShipping(w http.ResponseWriter, r *http.Request) {
	var req *domain.ScheduleOrderRequest
	err := json.NewDecoder(r.Body).Decode(&req)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	orderId, err := primitive.ObjectIDFromHex(req.Id)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	order := &domain.Order{
		Id:              orderId,
		ShippingAddress: req.ShippingAddress,
	}
	err = handler.service.Create(order)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	w.WriteHeader(http.StatusOK)
}
