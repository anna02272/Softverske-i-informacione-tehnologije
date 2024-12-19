package startup

import (
	"context"
	"fmt"
	"github.com/gorilla/mux"
	"github.com/tamararankovic/microservices_demo/shipping_service/application"
	"github.com/tamararankovic/microservices_demo/shipping_service/domain"
	handlers2 "github.com/tamararankovic/microservices_demo/shipping_service/handlers"
	"github.com/tamararankovic/microservices_demo/shipping_service/startup/config"
	store2 "github.com/tamararankovic/microservices_demo/shipping_service/store"
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
			log.Printf("error closing db: %s\n", err)
		}
	}(mongoClient, context.Background())

	orderStore := server.initOrderStore(mongoClient)

	orderService := server.initOrderService(orderStore)

	orderHandler := server.initOrderHandler(orderService)

	server.start(orderHandler)
}

func (server *Server) initMongoClient() *mongo.Client {
	client, err := store2.GetClient(server.config.ShippingDBHost, server.config.ShippingDBPort)
	if err != nil {
		log.Fatal(err)
	}
	return client
}

func (server *Server) initOrderStore(client *mongo.Client) domain.OrderStore {
	store := store2.NewOrderMongoDBStore(client)
	store.DeleteAll()
	for _, Order := range orders {
		err := store.Insert(Order)
		if err != nil {
			log.Fatal(err)
		}
	}
	return store
}

func (server *Server) initOrderService(store domain.OrderStore) *application.OrderService {
	return application.NewOrderService(store)
}

func (server *Server) initOrderHandler(service *application.OrderService) *handlers2.OrderHandler {
	return handlers2.NewOrderHandler(service)
}

func (server *Server) start(orderHandler *handlers2.OrderHandler) {
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
