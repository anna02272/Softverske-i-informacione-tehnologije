package main

import (
	"example/command/client"
	"example/command/commands/handler"
	"example/command/config"
	"example/command/handlers"
	"example/command/store"
	"fmt"
	"github.com/EventStore/EventStore-Client-Go/esdb"
	"github.com/gorilla/mux"
	"log"
	"net/http"
	"os"
)

func main() {
	cfg := config.NewConfig()

	connString := fmt.Sprintf("esdb://%s:%s@%s:%s?tls=false", cfg.ESDBUser, cfg.ESDBPass, cfg.ESDBHost, cfg.ESDBPort)
	settings, err := esdb.ParseConnectionString(connString)
	if err != nil {
		log.Fatal(err)
	}
	esdbClient, err := esdb.NewClient(settings)
	if err != nil {
		log.Fatal(err)
	}

	eventStore := store.NewESDBStore(esdbClient)
	commandHandler := handler.NewHandler(eventStore)
	queryClientAddr := fmt.Sprintf("%s:%s", cfg.QueryServiceHost, cfg.QueryServicePort)
	queryClient := client.NewQueryServiceClient(queryClientAddr)

	router := mux.NewRouter()
	accountHandler := handlers.NewAccountHandler(commandHandler)
	accountHandler.Init(router)
	paymentHandler := handlers.NewPaymentHandler(commandHandler, queryClient)
	paymentHandler.Init(router)

	server := http.Server{
		Addr:    fmt.Sprintf(":%s", cfg.Port),
		Handler: router,
	}

	for err := server.ListenAndServe(); err != nil; {
		log.Println(err)
		os.Exit(0)
	}
}
