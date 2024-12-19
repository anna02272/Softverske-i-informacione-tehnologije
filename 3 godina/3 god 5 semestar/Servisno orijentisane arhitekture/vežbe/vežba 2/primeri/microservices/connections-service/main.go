package main

import (
	"log"
	"net/http"
	"soa/2023-2024/lab1/microservices/connections-service/handlers"
	"soa/2023-2024/lab1/microservices/connections-service/repositories"
	"soa/2023-2024/lab1/microservices/connections-service/services"

	"github.com/gorilla/mux"
)

func main() {
	connRepository, err := repositories.NewConnectionInMem()
	handleErr(err)

	connectionService, err := services.NewConnectionService(connRepository)
	handleErr(err)

	connectionHandler, err := handlers.NewConnectionHandler(connectionService)
	handleErr(err)

	r := mux.NewRouter()

	r.HandleFunc("/connections", connectionHandler.Create).Methods("POST")
	r.HandleFunc("/connections", connectionHandler.Delete).Methods("DELETE")
	r.HandleFunc("/users/{userId}/connections", connectionHandler.GetByUser).Methods("GET")

	r.Use(handlers.AuthMiddleware)

	srv := &http.Server{
		Handler: r,
		Addr:    ":8001",
	}
	log.Fatal(srv.ListenAndServe())
}

func handleErr(err error) {
	if err != nil {
		log.Fatalln(err)
	}
}
