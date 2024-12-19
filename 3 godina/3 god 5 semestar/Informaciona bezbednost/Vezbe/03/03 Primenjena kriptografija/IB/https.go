package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.HandleFunc("/", httpRequestHandler)
	err := http.ListenAndServeTLS(":8081", "cert/go-server.crt", "cert/go-server.key", nil)
	if err != nil {
		fmt.Println(err)
		return
	}
}

func httpRequestHandler(w http.ResponseWriter, req *http.Request) {
	fmt.Fprintf(w, "Hello, World!")
}
