package main

import (
	"Rest/cache"
	"Rest/data"
	"Rest/handlers"
	"context"
	"log"
	"net/http"
	"os"
	"os/signal"
	"time"

	gorillaHandlers "github.com/gorilla/handlers"
	"github.com/gorilla/mux"
)

func main() {
	//Reading from environment, if not set we will default it to 8080.
	//This allows flexibility in different environments (for eg. when running multiple docker api's and want to override the default port)
	port := os.Getenv("PORT")
	if len(port) == 0 {
		port = "8080"
	}

	//Initialize the logger we are going to use, with prefix and datetime for every log
	logger := log.New(os.Stdout, "[product-api] ", log.LstdFlags)
	loggerDb := log.New(os.Stdout, "[consul-db] ", log.LstdFlags)
	loggerCache := log.New(os.Stdout, "[redis-cache] ", log.LstdFlags)
	
	// NoSQL: Initialize Product Repository store
	store, err := data.New(loggerDb)
	if err != nil {
		logger.Fatal(err)
	}

	// NoSQL: Initalize Redis Client
	prCache := cache.New(loggerCache)
	// Test connection
	prCache.Ping()

	//Initialize the handler and inject said logger
	productsHandler := handlers.NewProductsHandler(logger, store, prCache)

	//Initialize the router and add a middleware for all the requests
	router := mux.NewRouter()
	router.Use(productsHandler.MiddlewareContentTypeSet)

	getRouter := router.Methods(http.MethodGet).Subrouter()
	getRouter.HandleFunc("/", productsHandler.GetProducts)
	
	getAllRouter := router.Methods(http.MethodGet).Subrouter()
	getAllRouter.HandleFunc("/all", productsHandler.GetAllProducts)
	// NoSQL TODO: Add midleware to check if cache has all Products list
	getAllRouter.Use(productsHandler.MiddlewareCacheAllHit)

	// NoSQL Bonus TODO: Add top N route
	getTopRouter := router.Methods(http.MethodGet).Subrouter()
	getTopRouter.HandleFunc("/top/{n}", productsHandler.GetTopProducts)

	// NoSQL: get by id handler mapping
	getByIdRouter := router.Methods(http.MethodGet).Subrouter()
	getByIdRouter.HandleFunc("/{id}", productsHandler.GetOneProduct)
	// NoSQL: add midleware to check if cache has requested Product
	getByIdRouter.Use(productsHandler.MiddlewareCacheHit)

	postRouter := router.Methods(http.MethodPost).Subrouter()
	postRouter.HandleFunc("/", productsHandler.PostProducts)
	postRouter.Use(productsHandler.MiddlewareProductValidation)

	
	putRouter := router.Methods(http.MethodPut).Subrouter()
	putRouter.HandleFunc("/{id}", productsHandler.PutProducts)
	putRouter.Use(productsHandler.MiddlewareProductValidation)

	// NoSQL: add delete all handler mapping
	deleteAllHandler := router.Methods(http.MethodDelete).Subrouter()
	deleteAllHandler.HandleFunc("/all", productsHandler.DeleteAllProducts)

	deleteHandler := router.Methods(http.MethodDelete).Subrouter()
	deleteHandler.HandleFunc("/{id}", productsHandler.DeleteProducts)

	//Set cors. Generally you wouldn't like to set cors to a "*". It is a wildcard and it will match any source.
	//Normally you would set this to a set of ip's you want this api to serve. If you have an associated frontend app
	//you would put the ip of the server where the frontend is running. The only time you don't need cors is when you
	//calling the api from the same ip, or when you are using the proxy (for eg. Nginx)
	cors := gorillaHandlers.CORS(gorillaHandlers.AllowedOrigins([]string{"*"}))

	//Initialize the server
	server := http.Server{
		Addr:         ":" + port,        // Addr optionally specifies the TCP address for the server to listen on, in the form "host:port". If empty, ":http" (port 80) is used.
		Handler:      cors(router),      // handler to invoke, http.DefaultServeMux if nil
		IdleTimeout:  120 * time.Second, // IdleTimeout is the maximum amount of time to wait for the next request when keep-alives are enabled.
		ReadTimeout:  1 * time.Second,   // ReadTimeout is the maximum duration for reading the entire request, including the body. A zero or negative value means there will be no timeout.
		// NoSQL: changed to simulate long database operation
		WriteTimeout: 5 * time.Second,   // WriteTimeout is the maximum duration before timing out writes of the response.
	}

	logger.Println("Server listening on port", port)
	//Distribute all the connections to goroutines
	go func() {
		err := server.ListenAndServe()
		if err != nil {
			logger.Fatal(err)
		}
	}()

	sigCh := make(chan os.Signal)
	signal.Notify(sigCh, os.Interrupt)
	signal.Notify(sigCh, os.Kill)

	//When we receive an interrupt or kill, if we don't have any current connections the code will terminate.
	//But if we do the code will stop receiving any new connections and wait for maximum of 30 seconds to finish all current requests.
	//After that the code will terminate.
	sig := <-sigCh
	logger.Println("Received terminate, graceful shutdown", sig)
	timeoutContext, cancel := context.WithTimeout(context.Background(), 30*time.Second)
	defer cancel()

	//Try to shutdown gracefully
	if server.Shutdown(timeoutContext) != nil {
		logger.Fatal("Cannot gracefully shutdown...")
	}
	logger.Println("Server stopped")
}
