package main

import (
	"example/common/messaging/nats"
	"example/profile/config"
	"example/profile/data"
	"example/profile/handlers"
	"fmt"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

const queueGroup = "profile-service"

func main() {
	cfg := config.NewConfig()

	repo := data.NewProfileRepo(data.Profiles)
	profileViewRepo := data.NewProfilePageViewRepo(data.ProfilePageViews)
	conn, err := nats.Connection(cfg.NatsHost, cfg.NatsPort, cfg.NatsUser, cfg.NatsPass)
	if err != nil {
		log.Fatal(err)
	}
	subscriber, err := nats.NewNATSSubscriber(conn, cfg.NatsSubject, queueGroup)
	if err != nil {
		log.Fatal(err)
	}
	_, err = handlers.NewProfileEventHandler(profileViewRepo, subscriber)
	if err != nil {
		log.Fatal(err)
	}
	publisher, err := nats.NewNATSPublisher(conn, cfg.NatsSubject)
	if err != nil {
		log.Fatal(err)
	}
	productHandler := handlers.NewProfileHandler(repo, profileViewRepo, publisher)
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
