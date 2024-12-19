package data

import (
	"context"
	"errors"
	"fmt"
	"go.opentelemetry.io/otel/codes"
	"go.opentelemetry.io/otel/trace"
)

type ProductRepository struct {
	Tracer   trace.Tracer
	Products map[int64]Product
}

func (r ProductRepository) GetProduct(ctx context.Context, id int64) (Product, error) {
	ctx, span := r.Tracer.Start(ctx, "ProductRepository.GetProduct")
	defer span.End()

	product, ok := r.Products[id]
	if !ok {
		err := errors.New(fmt.Sprintf("product ID = %d not found", id))
		span.SetStatus(codes.Error, err.Error())
		return Product{}, err
	}
	return product, nil
}
