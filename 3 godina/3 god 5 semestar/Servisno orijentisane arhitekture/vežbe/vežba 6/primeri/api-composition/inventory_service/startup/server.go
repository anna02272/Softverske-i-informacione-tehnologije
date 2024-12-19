package startup

import (
	"context"
	"fmt"
	"github.com/gorilla/mux"
	"github.com/tamararankovic/microservices_demo/inventory_service/application"
	"github.com/tamararankovic/microservices_demo/inventory_service/domain"
	"github.com/tamararankovic/microservices_demo/inventory_service/handlers"
	"github.com/tamararankovic/microservices_demo/inventory_service/startup/config"
	"github.com/tamararankovic/microservices_demo/inventory_service/store"
	"gorm.io/gorm"
	"log"
	"net/http"
	"os"
	"os/signal"
	"syscall"
	"time"
)

type Server struct {
	config *config.Config
}

func NewServer(config *config.Config) *Server {
	return &Server{
		config: config,
	}
}

func (server *Server) Start() {
	postgresClient := server.initPostgresClient()
	productStore := server.initProductStore(postgresClient)
	defer func(productStore domain.ProductStore) {
		err := productStore.Close()
		if err != nil {
			log.Printf("error closing db: %s", err)
		}
	}(productStore)

	productService := server.initProductService(productStore)

	productHandler := server.initProductHandler(productService)

	server.start(productHandler)
}

func (server *Server) initPostgresClient() *gorm.DB {
	client, err := store.GetClient(
		server.config.InventoryDBHost, server.config.InventoryDBUser,
		server.config.InventoryDBPass, server.config.InventoryDBName,
		server.config.InventoryDBPort)
	if err != nil {
		log.Fatal(err)
	}
	return client
}

func (server *Server) initProductStore(client *gorm.DB) domain.ProductStore {
	store, err := store.NewProductPostgresStore(client)
	if err != nil {
		log.Fatal(err)
	}
	store.DeleteAll()
	for _, Product := range products {
		err := store.Insert(Product)
		if err != nil {
			log.Fatal(err)
		}
	}
	return store
}

func (server *Server) initProductService(store domain.ProductStore) *application.ProductService {
	return application.NewProductService(store)
}

func (server *Server) initProductHandler(service *application.ProductService) *handlers.ProductHandler {
	return handlers.NewProductHandler(service)
}

func (server *Server) start(productHandler *handlers.ProductHandler) {
	r := mux.NewRouter()
	productHandler.Init(r)

	srv := &http.Server{
		Addr:    fmt.Sprintf(":%s", server.config.Port),
		Handler: r,
	}

	wait := time.Second * 15
	go func() {
		if err := srv.ListenAndServe(); err != nil {
			log.Println(err)
		}
	}()

	c := make(chan os.Signal, 1)

	signal.Notify(c, os.Interrupt)
	signal.Notify(c, syscall.SIGTERM)

	<-c

	ctx, cancel := context.WithTimeout(context.Background(), wait)
	defer cancel()

	if err := srv.Shutdown(ctx); err != nil {
		log.Fatalf("error shutting down server %s", err)
	}
	log.Println("server gracefully stopped")
}
