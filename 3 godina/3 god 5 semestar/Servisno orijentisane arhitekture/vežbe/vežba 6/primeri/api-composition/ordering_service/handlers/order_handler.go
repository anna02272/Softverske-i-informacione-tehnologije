package handlers

import (
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/tamararankovic/microservices_demo/ordering_service/application"
	"github.com/tamararankovic/microservices_demo/ordering_service/domain"
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
	r.HandleFunc("/", handler.CreateOrder).Methods("POST")
	r.HandleFunc("/{id}/details", handler.GetDetails).Methods("GET")
	http.Handle("/", r)
}

func (handler *OrderHandler) CreateOrder(w http.ResponseWriter, r *http.Request) {
	var req domain.CreateOrderRequest
	err := json.NewDecoder(r.Body).Decode(&req)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Println(req)

	err = handler.service.Create(&req.Order, req.ShippingAddress)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	w.WriteHeader(http.StatusOK)
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
	jsonResponse(order, w)
}

func (handler *OrderHandler) GetDetails(w http.ResponseWriter, r *http.Request) {
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
	order, err := handler.service.GetDetails(objectId)
	if err != nil {
		w.WriteHeader(http.StatusNotFound)
		return
	}

	jsonResponse(order, w)
}

func (handler *OrderHandler) GetAll(w http.ResponseWriter, r *http.Request) {
	orders, err := handler.service.GetAll()
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		return
	}
	jsonResponse(orders, w)
}
