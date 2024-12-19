package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
	"soa/2023-2024/lab1/microservices/posts-service/handlers"
	"soa/2023-2024/lab1/microservices/posts-service/repositories"
	"soa/2023-2024/lab1/microservices/posts-service/services"

	"github.com/gorilla/mux"
)

func main() {
	config := loadConfig()

	postRepository, err := repositories.NewPostPostgres(config["db_host"], config["db_port"], config["db_user"], config["db_pass"], config["db_name"])
	handleErr(err)

	connectionService, err := services.NewConnectionService(config["conn_service_address"])
	handleErr(err)
	postService, err := services.NewPostService(postRepository, connectionService)
	handleErr(err)

	postHanler, err := handlers.NewPostHandler(postService)
	handleErr(err)

	r := mux.NewRouter()

	r.HandleFunc("/posts", postHanler.Create).Methods(http.MethodPost)
	r.HandleFunc("/posts/{postId}/likes", postHanler.Like).Methods(http.MethodPost)
	r.HandleFunc("/users/{userId}/homefeed", postHanler.GetHomeFeed).Methods(http.MethodGet)

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
	config["conn_service_address"] = fmt.Sprintf("http://%s:%s", os.Getenv("CONNECTIONS_SERVICE_HOST"), os.Getenv("CONNECTIONS_SERVICE_PORT"))
	return config
}
