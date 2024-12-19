package main

import (
	"example/catalogue/config"
	"example/catalogue/data"
	"example/catalogue/handlers"
	"log"
	"net/http"
	"os"
	"os/signal"
	"syscall"

	"github.com/gorilla/mux"
)

func main() {
	cfg := config.GetConfig()

	productRepository := data.ProductRepository{Products: data.Products}
	productHandler := handlers.ProductHandler{Repo: productRepository}

	router := mux.NewRouter()
	router.HandleFunc("/{id}", productHandler.GetProduct).Methods("GET")

	server := http.Server{
		Addr:    cfg.Address,
		Handler: router,
	}

	go func() {
		if err := server.ListenAndServe(); err != nil {
			log.Fatal("server error: ", err)
		}
	}()

	stopCh := make(chan os.Signal)
	signal.Notify(stopCh, syscall.SIGTERM)

	<-stopCh

	if err := server.Close(); err != nil {
		log.Fatal("error while stopping server: ", err)
	}
}
