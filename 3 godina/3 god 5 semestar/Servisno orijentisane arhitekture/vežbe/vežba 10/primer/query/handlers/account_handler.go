package handlers

import (
	"example/query/domain"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

type GetAccountResponse struct {
	AccountNumber string
	HolderName    string
	Balance       uint32
}

type AccountHandler struct {
	store domain.AccountStore
}

func NewAccountHandler(store domain.AccountStore) AccountHandler {
	return AccountHandler{store: store}
}

func (h *AccountHandler) Init(r *mux.Router) {
	r.HandleFunc("/accounts/{id}", h.Get).Methods("GET")
	r.HandleFunc("/accounts", h.GetAll).Methods("GET")
}

func (h AccountHandler) Get(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	accountNumber, ok := vars["id"]
	if !ok {
		w.WriteHeader(http.StatusBadRequest)
		return
	}

	account, err := h.store.Read(accountNumber)
	if err != nil {
		log.Println(err)
		w.WriteHeader(http.StatusNotFound)
		return
	}

	jsonResponse(account, w)
}

func (h AccountHandler) GetAll(w http.ResponseWriter, r *http.Request) {
	accounts := h.store.ReadAll()
	jsonResponse(accounts, w)
}
