package handlers

import (
	"context"
	"net/http"
	"soa/2023-2024/lab1/monolith/services"
)

type AuthHandler struct {
	auth services.AuthService
}

func NewAuthHandler(auth services.AuthService) (AuthHandler, error) {
	return AuthHandler{
		auth: auth,
	}, nil
}

func (h AuthHandler) LogIn(w http.ResponseWriter, r *http.Request) {
	req := &struct {
		Username string
		Password string
	}{}
	err := readReq(req, r, w)
	if err != nil {
		return
	}

	token, err := h.auth.LogIn(req.Username, req.Password)
	if err != nil {
		writeErrorResp(err, w)
		return
	}

	resp := struct {
		Token string
	}{
		Token: token,
	}
	writeResp(resp, http.StatusOK, w)
}

type AuthMiddleware struct {
	auth services.AuthService
}

func NewAuthMiddleware(auth services.AuthService) (AuthMiddleware, error) {
	return AuthMiddleware{
		auth: auth,
	}, nil
}

func (m AuthMiddleware) Handle(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		token := r.Header.Get("Auth-Token")
		authenticated, err := m.auth.ResolveUser(token)
		if err == nil {
			r = r.WithContext(context.WithValue(r.Context(), "auth", &authenticated))
		}
		next.ServeHTTP(w, r)
	})
}
