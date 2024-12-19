package handlers

import (
	"12factorapp/data"
	"context"
	"fmt"
	"log"
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
)

type KeyProduct struct{}

type ProductsHandler struct {
	logger *log.Logger
	productRepo data.ProductRepo
}

//Injecting the logger makes this code much more testable.
func New(l *log.Logger, pr data.ProductRepo) *ProductsHandler {
	return &ProductsHandler{l, pr}
}

func (p *ProductsHandler) GetProducts(rw http.ResponseWriter, h *http.Request) {
	products := p.productRepo.GetProducts()
	err := products.ToJSON(rw)

	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Println("Unable to convert to json :", err)
		return
	}
}

func (p *ProductsHandler) GetAllProducts(rw http.ResponseWriter, h *http.Request) {
	allProducts := p.productRepo.GetAll()
	err := allProducts.ToJSON(rw)

	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Println("Unable to convert to json :", err)
		return
	}
}

func (p *ProductsHandler) PostProducts(rw http.ResponseWriter, h *http.Request) {
	prod := h.Context().Value(KeyProduct{}).(*data.Product)
	p.productRepo.AddProduct(prod)
	rw.WriteHeader(http.StatusCreated)
}

func (p *ProductsHandler) PutProducts(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id, _ := strconv.Atoi(vars["id"])

	prod := h.Context().Value(KeyProduct{}).(*data.Product)
	putErr := p.productRepo.PutProduct(prod, id)

	if putErr != nil {
		http.Error(rw, putErr.Error(), http.StatusBadRequest)
		p.logger.Println(putErr.Error())
		return
	}

	err := prod.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Println("Unable to convert to json :", err)
		return
	}
}

func (p *ProductsHandler) DeleteProducts(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id, err := strconv.Atoi(vars["id"])

	if err != nil {
		http.Error(rw, err.Error(), http.StatusBadRequest)
		p.logger.Println("Unable to convert from ascii to integer - input was :", vars["id"])
		return
	}

	err = p.productRepo.DeleteProduct(id)

	if err != nil {
		http.Error(rw, err.Error(), http.StatusBadRequest)
		p.logger.Println("Unable to delete product.", err)
		return
	}

	rw.WriteHeader(http.StatusOK)
}

//Middleware to try and decode the incoming body. When decoded we run the validation on it just to check if everything is okay
//with the model. If anything is wrong we terminate the execution and the code won't even hit the handler functions.
//With a key we bind what we read to the context of the current request. Later we use that key to get to the read value.

func (p *ProductsHandler) MiddlewareProductValidation(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		prod := &data.Product{}
		err := prod.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			p.logger.Println(err)
			return
		}

		err = prod.Validate()

		if err != nil {
			p.logger.Println("Error validating product", err)
			http.Error(rw, fmt.Sprintf("Error validating product: %s", err), http.StatusBadRequest)
			return
		}

		ctx := context.WithValue(h.Context(), KeyProduct{}, prod)
		h = h.WithContext(ctx)

		next.ServeHTTP(rw, h)
	})
}

//Middleware to centralize general logging and to add the header values for all requests.

func (p *ProductsHandler) MiddlewareContentTypeSet(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		p.logger.Println("Method [", h.Method, "] - Hit path :", h.URL.Path)

		rw.Header().Add("Content-Type", "application/json")

		next.ServeHTTP(rw, h)
	})
}
