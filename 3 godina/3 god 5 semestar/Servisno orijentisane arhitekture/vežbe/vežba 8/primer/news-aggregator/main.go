package main

import (
	"log"
	"net/http"
	"soa/2023-2024/lab7/news-aggregator/clients"
	"soa/2023-2024/lab7/news-aggregator/domain"
	"soa/2023-2024/lab7/news-aggregator/handlers"
	"time"

	"github.com/gorilla/mux"
	"github.com/sony/gobreaker"
)

const (
	BBCAddress = "http://bbc:8000"
	CNNAddress = "http://cnn:8000"
	NYTAddress = "http://nyt:8000"
)

func main() {
	log.SetFlags(log.Ldate | log.Ltime | log.Lmicroseconds | log.Lshortfile)

	sources := make([]domain.NewsSource, 0)

	bbcClient := &http.Client{
		Transport: &http.Transport{
			// maximum number of connections in the pool for all hosts
			MaxIdleConns: 10,
			// maximum number of connections in the pool per host
			MaxIdleConnsPerHost: 10,
			// maximum number of connections per host, both in the pool and in use
			MaxConnsPerHost: 10,
		},
	}
	cnnClient := &http.Client{
		Transport: &http.Transport{
			// maximum number of connections in the pool for all hosts
			MaxIdleConns: 10,
			// maximum number of connections in the pool per host
			MaxIdleConnsPerHost: 10,
			// maximum number of connections per host, both in the pool and in use
			MaxConnsPerHost: 10,
		},
	}
	nytClient := &http.Client{
		Transport: &http.Transport{
			// maximum number of connections in the pool for all hosts
			MaxIdleConns: 10,
			// maximum number of connections in the pool per host
			MaxIdleConnsPerHost: 10,
			// maximum number of connections per host, both in the pool and in use
			MaxConnsPerHost: 10,
		},
	}

	bbcBreaker := gobreaker.NewCircuitBreaker(
		gobreaker.Settings{
			Name:        "bbc",
			MaxRequests: 1,
			Timeout:     10 * time.Second,
			Interval:    0,
			ReadyToTrip: func(counts gobreaker.Counts) bool {
				return counts.ConsecutiveFailures > 2
			},
			OnStateChange: func(name string, from gobreaker.State, to gobreaker.State) {
				log.Printf("Circuit Breaker '%s' changed from '%s' to, %s'\n", name, from, to)
			},
			IsSuccessful: func(err error) bool {
				if err == nil {
					return true
				}
				errResp, ok := err.(domain.ErrResp)
				return ok && errResp.StatusCode >= 400 && errResp.StatusCode < 500
			},
		},
	)
	cnnBreaker := gobreaker.NewCircuitBreaker(
		gobreaker.Settings{
			Name:        "cnn",
			MaxRequests: 1,
			Timeout:     10 * time.Second,
			Interval:    0,
			ReadyToTrip: func(counts gobreaker.Counts) bool {
				return counts.ConsecutiveFailures > 2
			},
			OnStateChange: func(name string, from gobreaker.State, to gobreaker.State) {
				log.Printf("Circuit Breaker '%s' changed from '%s' to, %s'\n", name, from, to)
			},
			IsSuccessful: func(err error) bool {
				if err == nil {
					return true
				}
				errResp, ok := err.(domain.ErrResp)
				return ok && errResp.StatusCode >= 400 && errResp.StatusCode < 500
			},
		},
	)
	nytBreaker := gobreaker.NewCircuitBreaker(
		gobreaker.Settings{
			Name:        "nyt",
			MaxRequests: 1,
			Timeout:     10 * time.Second,
			Interval:    0,
			ReadyToTrip: func(counts gobreaker.Counts) bool {
				return counts.ConsecutiveFailures > 2
			},
			OnStateChange: func(name string, from gobreaker.State, to gobreaker.State) {
				log.Printf("Circuit Breaker '%s' changed from '%s' to, %s'\n", name, from, to)
			},
			IsSuccessful: func(err error) bool {
				if err == nil {
					return true
				}
				errResp, ok := err.(domain.ErrResp)
				return ok && errResp.StatusCode >= 400 && errResp.StatusCode < 500
			},
		},
	)

	bbc := clients.NewBBCClient(bbcClient, BBCAddress, bbcBreaker)
	cnn := clients.NewCNNClient(cnnClient, CNNAddress, cnnBreaker)
	nyt := clients.NewNYTClient(nytClient, NYTAddress, nytBreaker)

	sources = append(sources, bbc)
	sources = append(sources, cnn)
	sources = append(sources, nyt)

	articleHandler := handlers.NewArticleHandler(sources)

	r := mux.NewRouter()

	r.HandleFunc("/articles", articleHandler.Search).
		Methods(http.MethodGet).
		Queries("query", "{query}")

	srv := &http.Server{
		Handler: r,
		Addr:    ":8000",
	}
	log.Fatalln(srv.ListenAndServe())
}
