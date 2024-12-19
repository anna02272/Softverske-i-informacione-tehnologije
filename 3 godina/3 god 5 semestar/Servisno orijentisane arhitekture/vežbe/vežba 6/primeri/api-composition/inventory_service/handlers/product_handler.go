package handlers

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"github.com/tamararankovic/microservices_demo/inventory_service/application"
	"github.com/tamararankovic/microservices_demo/inventory_service/domain"
	"log"
	"net/http"
)

type ProductHandler struct {
	service *application.ProductService
}

func NewProductHandler(service *application.ProductService) *ProductHandler {
	return &ProductHandler{
		service: service,
	}
}

func (handler *ProductHandler) Init(r *mux.Router) {
	r.HandleFunc("/", handler.GetAll).Methods("GET")
	r.HandleFunc("/", handler.UpdateInventory).Methods("POST")
	http.Handle("/", r)
}

func (handler *ProductHandler) GetAll(w http.ResponseWriter, r *http.Request) {
	products, err := handler.service.GetAll()
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	jsonResponse(products, w)
}

func (handler *ProductHandler) UpdateInventory(w http.ResponseWriter, r *http.Request) {
	var req *domain.UpdateInventoryRequest
	err := json.NewDecoder(r.Body).Decode(&req)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	updateReq := make(map[*domain.Product]int64)
	for _, product := range req.Products {
		p := &domain.Product{
			ProductId: product.ProductId,
			ColorCode: product.ColorCode,
		}
		updateReq[p] = product.Quantity
	}

	err = handler.service.UpdateQuantityForAll(updateReq)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	w.WriteHeader(http.StatusOK)
}
