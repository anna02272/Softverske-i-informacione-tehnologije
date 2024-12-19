package handlers

import (
	"encoding/json"
	"example/ordering/data"
	"fmt"
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
	"go.opentelemetry.io/otel"
	"go.opentelemetry.io/otel/codes"
	"go.opentelemetry.io/otel/propagation"
	"go.opentelemetry.io/otel/trace"
)

type OrderHandler struct {
	Tracer                  trace.Tracer
	Repo                    data.OrderRepository
	CatalogueServiceAddress string
}

func (h OrderHandler) GetOrder(w http.ResponseWriter, r *http.Request) {
	ctx, span := h.Tracer.Start(r.Context(), "OrderHandler.GetOrder")
	defer span.End()

	vars := mux.Vars(r)
	id, err := strconv.Atoi(vars["id"])
	if err != nil {
		span.SetStatus(codes.Error, err.Error())
		w.WriteHeader(http.StatusBadRequest)
		return
	}

	order, err := h.Repo.GetOrder(ctx, int64(id))
	if err != nil {
		span.SetStatus(codes.Error, err.Error())
		w.WriteHeader(http.StatusNotFound)
		return
	}

	response := GetOrderResponse{
		Order: OrderDTO{
			Id:    order.Id,
			Items: make([]OrderItemDTO, len(order.Items), len(order.Items)),
		},
	}

	for i, item := range order.Items {
		response.Order.Items[i] = OrderItemDTO{
			Product: ProductDTO{
				Id: item.ProductId,
			},
			Quantity: item.Quantity,
		}

		getProductUrl := fmt.Sprintf("%s%d", h.CatalogueServiceAddress, item.ProductId)

		req, err := http.NewRequest(http.MethodGet, getProductUrl, nil)
		if err != nil {
			span.RecordError(err)
			continue
		}

		otel.GetTextMapPropagator().Inject(ctx, propagation.HeaderCarrier(req.Header))

		res, err := http.DefaultClient.Do(req)
		if err != nil {
			span.RecordError(err)
			continue
		}

		var product ProductDTO

		err = json.NewDecoder(res.Body).Decode(&product)
		if err != nil {
			span.RecordError(err)
			continue
		}

		response.Order.Items[i].Product = product
	}

	jsonResp, err := json.Marshal(response)
	if err != nil {
		span.SetStatus(codes.Error, err.Error())
		w.WriteHeader(http.StatusInternalServerError)
		return
	}

	span.SetStatus(codes.Ok, "")
	w.Write(jsonResp)
	w.WriteHeader(http.StatusOK)
}

type GetOrderResponse struct {
	Order OrderDTO
}

type OrderDTO struct {
	Id    int64
	Items []OrderItemDTO
}

type ProductDTO struct {
	Id          int64
	Name        string
	Description string
}

type OrderItemDTO struct {
	Product  ProductDTO
	Quantity uint16
}

func ExtractTraceInfoMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		ctx := otel.GetTextMapPropagator().Extract(r.Context(), propagation.HeaderCarrier(r.Header))
		next.ServeHTTP(w, r.WithContext(ctx))
	})
}
