package handlers

import (
	"context"
	"net/http"
	"soa/2023-2024/lab1/microservices/connections-service/domain"

	"github.com/google/uuid"
)

func AuthMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		token := r.Header.Get("Auth-Token")
		id, err := uuid.Parse(token)
		if err == nil {
			authenticated := domain.User{
				Id: id,
			}
			r = r.WithContext(context.WithValue(r.Context(), "auth", &authenticated))
		}
		next.ServeHTTP(w, r)
	})
}
