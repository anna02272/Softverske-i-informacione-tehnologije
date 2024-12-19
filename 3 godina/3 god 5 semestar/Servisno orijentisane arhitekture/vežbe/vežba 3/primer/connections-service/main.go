package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
	"soa/2023-2024/lab1/microservices/connections-service/handlers"
	"soa/2023-2024/lab1/microservices/connections-service/repositories"
	"soa/2023-2024/lab1/microservices/connections-service/services"

	"github.com/gorilla/mux"
)

func main() {
	config := loadConfig()

	connRepository, err := repositories.NewConnectionPostgres(config["db_host"], config["db_port"], config["db_user"], config["db_pass"], config["db_name"])
	handleErr(err)

	connectionService, err := services.NewConnectionService(connRepository)
	handleErr(err)

	connectionHandler, err := handlers.NewConnectionHandler(connectionService)
	handleErr(err)

	r := mux.NewRouter()

	r.HandleFunc("/connections", connectionHandler.Create).Methods(http.MethodPost)
	r.HandleFunc("/connections", connectionHandler.Delete).Methods(http.MethodDelete)
	r.HandleFunc("/users/{userId}/connections", connectionHandler.GetByUser).Methods(http.MethodGet)

	r.Use(handlers.AuthMiddleware)

	srv := &http.Server{
		Handler: r,
		Addr:    config["address"],
	}
	log.Fatal(srv.ListenAndServe())
}

func handleErr(err error) {
	if err != nil {
		log.Fatalln(err)
	}
}

func loadConfig() map[string]string {
	config := make(map[string]string)
	config["host"] = os.Getenv("HOST")
	config["port"] = os.Getenv("PORT")
	config["address"] = fmt.Sprintf(":%s", os.Getenv("PORT"))
	config["db_host"] = os.Getenv("DB_HOST")
	config["db_port"] = os.Getenv("DB_PORT")
	config["db_user"] = os.Getenv("DB_USER")
	config["db_pass"] = os.Getenv("DB_PASS")
	config["db_name"] = os.Getenv("DB_NAME")
	return config
}
