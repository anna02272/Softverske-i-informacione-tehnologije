package main

import (
	"Rest/data"
	"Rest/handlers"
	"context"
	"log"
	"net/http"
	"os"
	"os/signal"
	"time"

	gorillaHandlers "github.com/gorilla/handlers"
	"github.com/gorilla/mux"
)

func main() {
	//Reading from environment, if not set we will default it to 8080.
	//This allows flexibility in different environments (for eg. when running multiple docker api's and want to override the default port)
	port := os.Getenv("PORT")
	if len(port) == 0 {
		port = "8080"
	}

	// Initialize context
	timeoutContext, cancel := context.WithTimeout(context.Background(), 30*time.Second)
	defer cancel()

	//Initialize the logger we are going to use, with prefix and datetime for every log
	logger := log.New(os.Stdout, "[library-api] ", log.LstdFlags)
	storeLogger := log.New(os.Stdout, "[library-store] ", log.LstdFlags)

	// NoSQL: Initialize Library Repository store
	store, err := data.New(storeLogger)
	if err != nil {
		logger.Fatal(err)
	}

	//Initialize the handler and inject said logger
	libraryHandler := handlers.NewLibraryHandler(logger, store)

	//Initialize the router and add a middleware for all the requests
	router := mux.NewRouter()
	router.Use(libraryHandler.MiddlewareContentTypeSet)

	getAuthors := router.Methods(http.MethodGet).Subrouter()
	getAuthors.HandleFunc("/authors", libraryHandler.GetAuthors)

	postAuthor := router.Methods(http.MethodPost).Subrouter()
	postAuthor.HandleFunc("/authors", libraryHandler.CreateAuthor)
	postAuthor.Use(libraryHandler.MiddlewareAuthorDeserialization)

	getBooksByAuthor := router.Methods(http.MethodGet).Subrouter()
	getBooksByAuthor.HandleFunc("/authors/books/{authorId}", libraryHandler.GetBooksByAuthor)

	getBooks := router.Methods(http.MethodGet).Subrouter()
	getBooks.HandleFunc("/books", libraryHandler.GetBooks)

	postBook := router.Methods(http.MethodPost).Subrouter()
	postBook.HandleFunc("/books", libraryHandler.CreateBook)
	postBook.Use(libraryHandler.MiddlewareBookDeserialization)

	getBookByIsbn := router.Methods(http.MethodGet).Subrouter()
	getBookByIsbn.HandleFunc("/books/isbn/{isbn}", libraryHandler.GetBookByIsbn)

	getMembers := router.Methods(http.MethodGet).Subrouter()
	getMembers.HandleFunc("/members", libraryHandler.GetMembers)

	postMember := router.Methods(http.MethodPost).Subrouter()
	postMember.HandleFunc("/members", libraryHandler.CreateMember)
	postMember.Use(libraryHandler.MiddlewareMemberDeserialization)

	postBorrowing := router.Methods(http.MethodPost).Subrouter()
	postBorrowing.HandleFunc("/books/borrow/{isbn}/{memberId}", libraryHandler.BorrowBook)

	postReturning := router.Methods(http.MethodPost).Subrouter()
	postReturning.HandleFunc("/books/return/{isbn}/{memberId}", libraryHandler.ReturnBook)

	getBorrowingsByMember := router.Methods(http.MethodGet).Subrouter()
	getBorrowingsByMember.HandleFunc("/borrowings/{memberId}", libraryHandler.GetBorrowingsByMember)

	cors := gorillaHandlers.CORS(gorillaHandlers.AllowedOrigins([]string{"*"}))

	//Initialize the server
	server := http.Server{
		Addr:         ":" + port,
		Handler:      cors(router),
		IdleTimeout:  120 * time.Second,
		ReadTimeout:  1 * time.Second,
		WriteTimeout: 1 * time.Second,
	}

	logger.Println("Server listening on port", port)
	//Distribute all the connections to goroutines
	go func() {
		err := server.ListenAndServe()
		if err != nil {
			logger.Fatal(err)
		}
	}()

	sigCh := make(chan os.Signal)
	signal.Notify(sigCh, os.Interrupt)
	signal.Notify(sigCh, os.Kill)

	sig := <-sigCh
	logger.Println("Received terminate, graceful shutdown", sig)

	//Try to shutdown gracefully
	if server.Shutdown(timeoutContext) != nil {
		logger.Fatal("Cannot gracefully shutdown...")
	}
	logger.Println("Server stopped")
}
