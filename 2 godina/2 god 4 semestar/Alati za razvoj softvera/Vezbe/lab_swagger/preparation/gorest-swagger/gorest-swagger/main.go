//Post API
//
//    Title: Post API
//
//    Schemes: http
//    Version: 0.0.1
//    BasePath: /
//
//    Produces:
//      - application/json
//
// swagger:meta
package main

import (
	"context"
	"log"
	"net/http"
	"os"
	"os/signal"
	"syscall"
	"time"

	"github.com/go-openapi/runtime/middleware"
	"github.com/gorilla/mux"
)

func main() {
	quit := make(chan os.Signal)
	signal.Notify(quit, os.Interrupt, syscall.SIGTERM)

	router := mux.NewRouter()
	router.StrictSlash(true)

	server := postServer{
		data: map[string]*RequestPost{},
	}
	router.HandleFunc("/post/", server.createPostHandler).Methods("POST")
	router.HandleFunc("/posts/", server.getAllHandler).Methods("GET")
	router.HandleFunc("/post/{id}/", server.getPostHandler).Methods("GET")
	router.HandleFunc("/post/{id}/", server.delPostHandler).Methods("DELETE")
	router.HandleFunc("/swagger.yaml", server.swaggerHandler).Methods("GET")

	// SwaggerUI
	optionsDevelopers := middleware.SwaggerUIOpts{SpecURL: "swagger.yaml"}
	developerDocumentationHandler := middleware.SwaggerUI(optionsDevelopers, nil)
	router.Handle("/docs", developerDocumentationHandler)

	// ReDoc
	// optionsShared := middleware.RedocOpts{SpecURL: "/swagger.yaml"}
	// sharedDocumentationHandler := middleware.Redoc(optionsShared, nil)
	// router.Handle("/docs", sharedDocumentationHandler)

	// start server
	srv := &http.Server{Addr: "0.0.0.0:8000", Handler: router}
	go func() {
		log.Println("server starting")
		if err := srv.ListenAndServe(); err != nil {
			if err != http.ErrServerClosed {
				log.Fatal(err)
			}
		}
	}()

	<-quit

	log.Println("service shutting down ...")

	// gracefully stop server
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	if err := srv.Shutdown(ctx); err != nil {
		log.Fatal(err)
	}
	log.Println("server stopped")
}
