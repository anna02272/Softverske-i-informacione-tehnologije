package main

import (
	"context"
	"example/proto/hello"
	"net"

	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

type HelloServer struct {
	hello.UnimplementedHelloServer
}

func NewHelloServer() hello.HelloServer {
	return HelloServer{}
}

// SayHello implements hello.HelloServer.
func (HelloServer) SayHello(ctx context.Context, req *hello.HelloReq) (*hello.HelloResp, error) {
	return &hello.HelloResp{
		Greeting: "Hello " + req.Name + "!",
	}, nil
}

func main() {
	listener, err := net.Listen("tcp", ":8001")
	if err != nil {
		panic(err)
	}

	server := grpc.NewServer()
	hello.RegisterHelloServer(server, NewHelloServer())
	reflection.Register(server)
	server.Serve(listener)
}
