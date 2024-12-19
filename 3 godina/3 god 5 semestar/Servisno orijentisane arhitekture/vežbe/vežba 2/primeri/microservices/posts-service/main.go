package main

import (
	"log"
	"net/http"
	"soa/2023-2024/lab1/microservices/posts-service/handlers"
	"soa/2023-2024/lab1/microservices/posts-service/repositories"
	"soa/2023-2024/lab1/microservices/posts-service/services"

	"github.com/gorilla/mux"
)

func main() {
	postRepository, err := repositories.NewPostInMem()
	handleErr(err)

	connectionService, err := services.NewConnectionService("http://localhost:8001")
	handleErr(err)
	postService, err := services.NewPostService(postRepository, connectionService)
	handleErr(err)

	postHanler, err := handlers.NewPostHandler(postService)
	handleErr(err)

	r := mux.NewRouter()

	r.HandleFunc("/posts", postHanler.Create).Methods("POST")
	r.HandleFunc("/posts/{postId}/likes", postHanler.Like).Methods("POST")
	r.HandleFunc("/users/{userId}/homefeed", postHanler.GetHomeFeed).Methods("GET")

	r.Use(handlers.AuthMiddleware)

	srv := &http.Server{
		Handler: r,
		Addr:    ":8002",
	}
	log.Fatal(srv.ListenAndServe())
}

func handleErr(err error) {
	if err != nil {
		log.Fatalln(err)
	}
}
