package main

import (
	"context"
	"github.com/gorilla/mux"
	"log"
	"net/http"
	"os"
	"os/signal"
	"syscall"
	"time"
	ps "github.com/milossimic/gorest/poststore"
)

func main() {
	quit := make(chan os.Signal)
	signal.Notify(quit, os.Interrupt, syscall.SIGTERM)

	router := mux.NewRouter()
	router.StrictSlash(true)

	store, err := ps.New()
	if err != nil {
		log.Fatal(err)
	}

	server := postServer{
		store: store,
	}
	router.HandleFunc("/post/", count(server.createPostHandler)).Methods("POST")
	router.HandleFunc("/posts/", count(server.getAllHandler)).Methods("GET")
	router.HandleFunc("/post/{id}/", count(server.getPostHandler)).Methods("GET")
	router.HandleFunc("/post/{id}/", count(server.delPostHandler)).Methods("DELETE")
	router.Path("/metrics").Handler(metricsHandler())

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
