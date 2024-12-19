package handlers

import (
	"net/http"
	"soa/2023-2024/lab1/monolith/services"
)

type UserHandler struct {
	users services.UserService
}

func NewUserHandler(users services.UserService) (UserHandler, error) {
	return UserHandler{
		users: users,
	}, nil
}

func (h UserHandler) Create(w http.ResponseWriter, r *http.Request) {
	req := &struct {
		Username string
		Password string
	}{}
	err := readReq(req, r, w)
	if err != nil {
		return
	}

	user, err := h.users.Create(req.Username, req.Password)
	if err != nil {
		writeErrorResp(err, w)
		return
	}

	resp := struct {
		Id       string
		Username string
	}{
		Id:       user.Id.String(),
		Username: user.Username,
	}
	writeResp(resp, http.StatusCreated, w)
}
