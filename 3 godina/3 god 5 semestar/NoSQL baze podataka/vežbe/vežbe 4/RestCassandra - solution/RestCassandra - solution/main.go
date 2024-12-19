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
	logger := log.New(os.Stdout, "[student-api] ", log.LstdFlags)
	storeLogger := log.New(os.Stdout, "[student-store] ", log.LstdFlags)

	// NoSQL: Initialize Product Repository store
	store, err := data.New(storeLogger)
	if err != nil {
		logger.Fatal(err)
	}
	defer store.CloseSession()
	store.CreateTables()

	//Initialize the handler and inject said logger
	studentsHandler := handlers.NewStudentsHandler(logger, store)

	//Initialize the router and add a middleware for all the requests
	router := mux.NewRouter()
	router.Use(studentsHandler.MiddlewareContentTypeSet)
	
	getStudentIds := router.Methods(http.MethodGet).Subrouter()
	getStudentIds.HandleFunc("/students", studentsHandler.GetAllStudentIds)

	getPredmetIds := router.Methods(http.MethodGet).Subrouter()
	getPredmetIds.HandleFunc("/predmets", studentsHandler.GetAllPredmetIds)
	
	getIspitiByStudent := router.Methods(http.MethodGet).Subrouter()
	getIspitiByStudent.HandleFunc("/students/{id}", studentsHandler.GetIspitiByStudent)
	
	getIspitiByPredmetAndSmer := router.Methods(http.MethodGet).Subrouter()
	getIspitiByPredmetAndSmer.HandleFunc("/predmets/{id}", studentsHandler.GetIspitiByPredmetAndSmer)
	
	getStudentiBySmer := router.Methods(http.MethodGet).Subrouter()
	getStudentiBySmer.HandleFunc("/smers/{id}", studentsHandler.GetStudentiBySmer)

	postIspitForStudent := router.Methods(http.MethodPost).Subrouter()
	postIspitForStudent.HandleFunc("/students", studentsHandler.CraeteIspitForStudent)
	postIspitForStudent.Use(studentsHandler.MiddlewareOcenaForStudentDeserialization)

	postIspitForPredmetAndSmer := router.Methods(http.MethodPost).Subrouter()
	postIspitForPredmetAndSmer.HandleFunc("/predmets", studentsHandler.CreateIspitForPredmetAndSmer)
	postIspitForPredmetAndSmer.Use(studentsHandler.MiddlewareOcenaForPredmetDeserialization)

	postStudentForSmer := router.Methods(http.MethodPost).Subrouter()
	postStudentForSmer.HandleFunc("/smers", studentsHandler.CreateStudentForSmer)
	postStudentForSmer.Use(studentsHandler.MiddlewareStudentForSmerDeserialization)

	putStudentForSmer := router.Methods(http.MethodPut).Subrouter()
	putStudentForSmer.HandleFunc("/smers/{smerId}/{studentId}/{indeks}", studentsHandler.UpdateStudentBySmer)

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
