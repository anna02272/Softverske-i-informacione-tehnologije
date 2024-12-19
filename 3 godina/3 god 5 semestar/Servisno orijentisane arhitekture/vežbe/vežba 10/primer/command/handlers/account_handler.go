package handlers

import (
	"encoding/json"
	"example/command/commands/create_account"
	"example/command/commands/handler"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

type CreateAccountRequest struct {
	HolderName string
}

type AccountHandler struct {
	handler handler.Handler
}

func NewAccountHandler(handler handler.Handler) AccountHandler {
	return AccountHandler{handler: handler}
}

func (h *AccountHandler) Init(r *mux.Router) {
	r.HandleFunc("/accounts", h.Create).Methods("POST")
}

func (h AccountHandler) Create(w http.ResponseWriter, r *http.Request) {
	var req CreateAccountRequest
	err := json.NewDecoder(r.Body).Decode(&req)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	command := create_account.NewCommand(req.HolderName)
	err = h.handler.Handle(command)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.WriteHeader(http.StatusCreated)
}
