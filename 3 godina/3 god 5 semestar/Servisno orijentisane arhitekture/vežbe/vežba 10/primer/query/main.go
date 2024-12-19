package main

import (
	"example/query/config"
	"example/query/events"
	"example/query/handlers"
	"example/query/store"
	"example/query/stream"
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

	accountStore := store.NewAccountStore()
	handler := events.NewEventHandler(accountStore)
	eventStream, err := stream.NewESDBEventStream(esdbClient, cfg.ESDBGroup)
	if err != nil {
		log.Fatal(err)
	}
	go eventStream.Process(handler.Handle)

	router := mux.NewRouter()
	accountHandler := handlers.NewAccountHandler(accountStore)
	accountHandler.Init(router)

	server := http.Server{
		Addr:    fmt.Sprintf(":%s", cfg.Port),
		Handler: router,
	}

	for err := server.ListenAndServe(); err != nil; {
		log.Println(err)
		os.Exit(0)
	}
}
