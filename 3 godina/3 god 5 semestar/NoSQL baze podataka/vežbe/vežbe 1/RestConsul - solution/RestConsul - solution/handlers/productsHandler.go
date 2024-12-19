package handlers

import (
	"Rest/data"
	"context"
	"fmt"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

type KeyProduct struct{}

type ProductsHandler struct {
	logger *log.Logger
	// NoSQL: injecting product repository
	repo *data.ProductRepo
}

//Injecting the logger makes this code much more testable.
func NewProductsHandler(l *log.Logger, r *data.ProductRepo) *ProductsHandler {
	return &ProductsHandler{l, r}
}

// NoSQL: TODO - get active products
func (p *ProductsHandler) GetProducts(rw http.ResponseWriter, h *http.Request) {
	// NoSQL: call repository method for getting all active products
	// Ideas?
	// Is this a good database structure for this kind of query?
	products, err := p.repo.GetAll()
	if err != nil {
		http.Error(rw, "Database exception", http.StatusInternalServerError)
		p.logger.Fatal("Database exception:", err)
		return
	}

	activeProducts := data.Products{}
	for _, product := range products {
		if product.DeletedOn == "" {
			activeProducts = append(activeProducts, product)
		}
	}

	err = activeProducts.ToJSON(rw)

	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (p *ProductsHandler) GetAllProducts(rw http.ResponseWriter, h *http.Request) {
	allProducts, err := p.repo.GetAll()
	if err != nil {
		http.Error(rw, "Database exception", http.StatusInternalServerError)
		p.logger.Fatal("Database exception: ", err)
	}

	err = allProducts.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (p *ProductsHandler) GetOneProduct(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id := vars["id"]
	
	product, err := p.repo.Get(id)
	if err != nil {
		http.Error(rw, "Database exception", http.StatusInternalServerError)
		p.logger.Fatal("Database exception: ", err)
	}

	if product == nil {
		http.Error(rw, "Product with given id not found", http.StatusNotFound)
		p.logger.Printf("Product with id: '%s' not found", id)
		return
	}

	err = product.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (p *ProductsHandler) GetProductsByType(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	productType := vars["type"]
	
	productByType, err := p.repo.GetByType(productType)
	if err != nil {
		http.Error(rw, "Database exception", http.StatusInternalServerError)
		p.logger.Fatal("Database exception: ", err)
	}

	err = productByType.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (p *ProductsHandler) PostProducts(rw http.ResponseWriter, h *http.Request) {
	prod := h.Context().Value(KeyProduct{}).(*data.Product)
	p.repo.Post(prod)
	// NoSQL: Bonus - create inside nested key structure
	p.repo.PostByType(prod)
	rw.WriteHeader(http.StatusCreated)
}

// NoSQL: TODO - update Product
func (p *ProductsHandler) PutProducts(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id := vars["id"]

	prod := h.Context().Value(KeyProduct{}).(*data.Product)

	// NoSQL: if id not set in the body, sets it here
	prod.ID = id

	productDb, err := p.repo.Get(id)
	if err != nil {
		http.Error(rw, "Database exception", http.StatusInternalServerError)
		p.logger.Fatal("Database exception:", err)
		return
	}
	// NoSQL: check if entity exists before updating
	if productDb == nil {
		http.Error(rw, "Product with given id not found", http.StatusNotFound)
		p.logger.Printf("Product with id: '%s' not found", id)
		return
	}
	
	// NoSQL: call repository method for updating a product
	// How do we update a key:value pair? -> By calling consul kv put for existing key
	prod, err = p.repo.Put(id, prod)
	// NoSQL: Don't forget to handle errors! :)
	if err != nil {
		http.Error(rw, "Database exception", http.StatusInternalServerError)
		p.logger.Fatal("Database exception:", err)
		return
	}

	err = prod.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}

	rw.WriteHeader(http.StatusOK)
}

func (p *ProductsHandler) DeleteProducts(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id := vars["id"]

	err := p.repo.Delete(id)

	if err != nil {
		http.Error(rw, err.Error(), http.StatusBadRequest)
		p.logger.Fatal("Unable to delete product.", err)
		return
	}

	rw.WriteHeader(http.StatusOK)
}

// NoSQL: Check if delete all works correctly
func (p *ProductsHandler) DeleteAllProducts(rw http.ResponseWriter, h *http.Request) {
	
	err := p.repo.DeleteAll()
	if err != nil {
		http.Error(rw, "Database exception", http.StatusInternalServerError)
		p.logger.Fatal("Database exception:", err)
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
			p.logger.Fatal(err)
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
