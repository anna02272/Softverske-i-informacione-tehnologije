package data

import (
	"context"
	"errors"
	"fmt"
	"go.opentelemetry.io/otel/codes"
	"go.opentelemetry.io/otel/trace"
)

type OrderRepository struct {
	Tracer trace.Tracer
	Orders map[int64]Order
}

func (r OrderRepository) GetOrder(ctx context.Context, id int64) (Order, error) {
	ctx, span := r.Tracer.Start(ctx, "OrderRepository.GetOrder")
	defer span.End()

	order, ok := r.Orders[id]
	if !ok {
		err := errors.New(fmt.Sprintf("order ID = %d not found", id))
		span.SetStatus(codes.Error, err.Error())
		return Order{}, err
	}
	return order, nil
}
