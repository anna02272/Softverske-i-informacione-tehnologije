package handlers

import (
	"Rest/cache"
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
	// NoSQL: injecting product cache
	cache *cache.ProductCache
}

//Injecting the logger makes this code much more testable.
func NewProductsHandler(l *log.Logger, r *data.ProductRepo, c *cache.ProductCache) *ProductsHandler {
	return &ProductsHandler{l, r, c}
}

func (p *ProductsHandler) GetProducts(rw http.ResponseWriter, h *http.Request) {
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
	
	// If method called, Product not present in cache -> get it from DB
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
	// And add to cache if found
	err = p.cache.Post(product)
	if err != nil {
		p.logger.Println("Unable to write to cache:", err)
	}

	err = product.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (p *ProductsHandler) PostProducts(rw http.ResponseWriter, h *http.Request) {
	prod := h.Context().Value(KeyProduct{}).(*data.Product)
	p.repo.Post(prod)
	rw.WriteHeader(http.StatusCreated)
}

func (p *ProductsHandler) PutProducts(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id := vars["id"]

	prod := h.Context().Value(KeyProduct{}).(*data.Product)

	prod.ID = id

	productDb, err := p.repo.Get(id)
	if err != nil {
		http.Error(rw, "Database exception", http.StatusInternalServerError)
		p.logger.Fatal("Database exception:", err)
		return
	}
	if productDb == nil {
		http.Error(rw, "Product with given id not found", http.StatusNotFound)
		p.logger.Printf("Product with id: '%s' not found", id)
		return
	}
	
	prod, err = p.repo.Put(id, prod)
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

// NoSQL: Middleware to check if we have a cache hit. If so, return value from cache. If not, call handler method.

func (p *ProductsHandler) MiddlewareCacheHit(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		vars := mux.Vars(h)
		id := vars["id"]
		// NoSQL: first look in the cache
		product, err := p.cache.Get(id)
		if err != nil {
			// If Product not present in cache, continue execution to handler method
			next.ServeHTTP(rw, h)
		} else {
			// If Product present in cache, return Product from cache
			err = product.ToJSON(rw)
			if err != nil {
				http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
				p.logger.Fatal("Unable to convert to json :", err)
				return
			}
		}
	})
}
