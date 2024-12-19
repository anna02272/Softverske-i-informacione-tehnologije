package handlers

import (
	"context"
	"example/grpc/proto/greeter"
	"fmt"
	"go.opentelemetry.io/otel/trace"
)

type GreeterHandler struct {
	greeter.UnimplementedGreeterServiceServer
	Tracer trace.Tracer
}

func (h GreeterHandler) Greet(ctx context.Context, request *greeter.Request) (*greeter.Response, error) {
	ctx, span := h.Tracer.Start(ctx, "GreeterHandler.Greet")
	defer span.End()

	return &greeter.Response{
		Greeting: fmt.Sprintf("Hi %s!", request.Name),
	}, nil
}
