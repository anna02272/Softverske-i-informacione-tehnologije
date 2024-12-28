package main

import (
	"github.com/prometheus/client_golang/prometheus"
	"github.com/prometheus/client_golang/prometheus/promhttp"
	"net/http"
)

var (
	// Initial count.
	currentCount = 0

	// The Prometheus metric that will be exposed.
	httpHits = prometheus.NewCounter(
		prometheus.CounterOpts{
			Name: "my_app_http_hit_total",
			Help: "Total number of http hits.",
		},
	)

	// The Prometheus metric that will be exposed.
	getPosts = prometheus.NewCounter(
		prometheus.CounterOpts{
			Name: "get_post_hit_total",
			Help: "Total number of get posts hits.",
		},
	)

	// Add all metrics that will be resisted
	metricsList = []prometheus.Collector{
		httpHits, getPosts,
	}

	// Prometheus Registry to register metrics.
	prometheusRegistry = prometheus.NewRegistry()
)

func init() {
	// Register metrics that will be exposed.
	prometheusRegistry.MustRegister(metricsList...)
}

func metricsHandler() http.Handler {
	return promhttp.HandlerFor(prometheusRegistry, promhttp.HandlerOpts{})
}

func count(f func(http.ResponseWriter, *http.Request)) func(http.ResponseWriter, *http.Request) {
	return func(w http.ResponseWriter, r *http.Request) {
		httpHits.Inc()
		f(w, r) // original function call
	}
}

func countGetPosts(f func(http.ResponseWriter, *http.Request)) func(http.ResponseWriter, *http.Request) {
	return func(w http.ResponseWriter, r *http.Request) {
		httpHits.Inc()
		getPosts.Inc()
		f(w, r) // original function call
	}
}
