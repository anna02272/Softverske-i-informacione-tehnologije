package main

import (
	"example/common/messaging/nats"
	"example/follow/config"
	"example/follow/data"
	"example/follow/handlers"
	"fmt"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

func main() {
	cfg := config.NewConfig()

	repo := data.NewFollowingRepository(data.Followers, data.Follows)
	conn, err := nats.Connection(cfg.NatsHost, cfg.NatsPort, cfg.NatsUser, cfg.NatsPass)
	if err != nil {
		log.Fatal(err)
	}
	publisher, err := nats.NewNATSPublisher(conn, cfg.NatsSubject)
	if err != nil {
		log.Fatal(err)
	}
	productHandler := handlers.NewFollowingHandler(repo, publisher)
	router := mux.NewRouter()
	productHandler.Init(router)

	server := http.Server{
		Handler: router,
		Addr:    fmt.Sprintf(":%s", cfg.Port),
	}

	for err = server.ListenAndServe(); err != nil; {
		log.Fatal(err)
	}
}
