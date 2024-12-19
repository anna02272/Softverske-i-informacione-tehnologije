package main

import (
	"context"
	"example/proto/hello"
	"log"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

func main() {
	conn, err := grpc.Dial("localhost:8001", grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		panic(err)
	}

	client := hello.NewHelloClient(conn)
	resp, err := client.SayHello(context.Background(), &hello.HelloReq{Name: "pera"})
	if err != nil {
		panic(err)
	}
	log.Println(resp.Greeting)
}
