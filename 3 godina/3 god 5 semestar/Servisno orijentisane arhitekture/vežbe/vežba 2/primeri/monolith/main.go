package main

import (
	"log"
	"net/http"
	"soa/2023-2024/lab1/monolith/handlers"
	"soa/2023-2024/lab1/monolith/repositories"
	"soa/2023-2024/lab1/monolith/services"

	"github.com/gorilla/mux"
)

func main() {
	userRepository, err := repositories.NewUserInMem()
	handleErr(err)
	postRepository, err := repositories.NewPostInMem(userRepository)
	handleErr(err)
	connRepository, err := repositories.NewConnectionInMem(userRepository)
	handleErr(err)

	userService, err := services.NewUserService(userRepository)
	handleErr(err)
	authService, err := services.NewAuthService(userRepository)
	handleErr(err)
	connectionService, err := services.NewConnectionService(connRepository, userService)
	handleErr(err)
	postService, err := services.NewPostService(postRepository, connectionService, userService)
	handleErr(err)

	userHandler, err := handlers.NewUserHandler(userService)
	handleErr(err)
	authHandler, err := handlers.NewAuthHandler(authService)
	handleErr(err)
	postHanler, err := handlers.NewPostHandler(postService)
	handleErr(err)
	connectionHandler, err := handlers.NewConnectionHandler(connectionService)
	handleErr(err)

	authMiddleware, err := handlers.NewAuthMiddleware(authService)
	handleErr(err)

	r := mux.NewRouter()

	r.HandleFunc("/users", userHandler.Create).Methods("POST")
	r.HandleFunc("/auth", authHandler.LogIn).Methods("POST")
	r.HandleFunc("/posts", postHanler.Create).Methods("POST")
	r.HandleFunc("/posts/{postId}/likes", postHanler.Like).Methods("POST")
	r.HandleFunc("/users/{userId}/homefeed", postHanler.GetHomeFeed).Methods("GET")
	r.HandleFunc("/connections", connectionHandler.Create).Methods("POST")
	r.HandleFunc("/connections", connectionHandler.Delete).Methods("DELETE")
	r.HandleFunc("/users/{userId}/connections", connectionHandler.GetByUser).Methods("GET")

	r.Use(authMiddleware.Handle)

	srv := &http.Server{
		Handler: r,
		Addr:    ":8000",
	}
	log.Fatal(srv.ListenAndServe())
}

func handleErr(err error) {
	if err != nil {
		log.Fatalln(err)
	}
}
