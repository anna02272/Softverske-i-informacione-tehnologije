package startup

import (
	"context"
	"fmt"
	"github.com/gorilla/mux"
	"github.com/tamararankovic/microservices_demo/ordering_service/application"
	"github.com/tamararankovic/microservices_demo/ordering_service/client/inventory"
	"github.com/tamararankovic/microservices_demo/ordering_service/client/shipping"
	"github.com/tamararankovic/microservices_demo/ordering_service/domain"
	handlers "github.com/tamararankovic/microservices_demo/ordering_service/handlers"
	"github.com/tamararankovic/microservices_demo/ordering_service/startup/config"
	store2 "github.com/tamararankovic/microservices_demo/ordering_service/store"
	"go.mongodb.org/mongo-driver/mongo"
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
	mongoClient := server.initMongoClient()
	defer func(mongoClient *mongo.Client, ctx context.Context) {
		err := mongoClient.Disconnect(ctx)
		if err != nil {

		}
	}(mongoClient, context.Background())

	orderStore := server.initOrderStore(mongoClient)

	inventoryClient := server.initInventoryClient()
	shippingClient := server.initShippingClient()
	orderService := server.initOrderService(orderStore, inventoryClient, shippingClient)

	orderHandler := server.initOrderHandler(orderService)

	server.start(orderHandler)
}

func (server *Server) initMongoClient() *mongo.Client {
	client, err := store2.GetClient(server.config.OrderingDBHost, server.config.OrderingDBPort)
	if err != nil {
		log.Fatal(err)
	}
	return client
}

func (server *Server) initOrderStore(client *mongo.Client) domain.OrderStore {
	store := store2.NewOrderMongoDBStore(client)
	store.DeleteAll()
	for _, order := range orders {
		err := store.Insert(order)
		if err != nil {
			log.Fatal(err)
		}
	}
	return store
}

func (server *Server) initInventoryClient() inventory.Client {
	return inventory.NewClient(server.config.InventoryHost, server.config.InventoryPort)
}

func (server *Server) initShippingClient() shipping.Client {
	return shipping.NewClient(server.config.ShippingHost, server.config.ShippingPort)
}

func (server *Server) initOrderService(store domain.OrderStore, inventoryClient inventory.Client, shippingClient shipping.Client) *application.OrderService {
	return application.NewOrderService(store, inventoryClient, shippingClient)
}

func (server *Server) initOrderHandler(service *application.OrderService) *handlers.OrderHandler {
	return handlers.NewOrderHandler(service)
}

func (server *Server) start(orderHandler *handlers.OrderHandler) {
	r := mux.NewRouter()
	orderHandler.Init(r)

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

	signal.Notify(c, syscall.SIGTERM)

	<-c

	ctx, cancel := context.WithTimeout(context.Background(), wait)
	defer cancel()

	if err := srv.Shutdown(ctx); err != nil {
		log.Fatalf("error shutting down server %s", err)
	}
	log.Println("server gracefully stopped")
}
