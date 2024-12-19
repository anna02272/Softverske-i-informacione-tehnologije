package handlers

import (
	"encoding/json"
	"errors"
	"example/command/client"
	"example/command/commands/handler"
	"example/command/commands/make_payment"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

type MakePaymentRequest struct {
	PayerAccountNumber string
	PayeeAccountNumber string
	Amount             uint32
}

type PaymentHandler struct {
	handler     handler.Handler
	queryClient client.QueryServiceClient
}

func NewPaymentHandler(handler handler.Handler, queryClient client.QueryServiceClient) PaymentHandler {
	return PaymentHandler{handler: handler, queryClient: queryClient}
}

func (h *PaymentHandler) Init(r *mux.Router) {
	r.HandleFunc("/payments", h.Create).Methods("POST")
}

func (h PaymentHandler) Create(w http.ResponseWriter, r *http.Request) {
	var req MakePaymentRequest
	err := json.NewDecoder(r.Body).Decode(&req)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	payerAccount, err := h.queryClient.GetAccount(req.PayerAccountNumber)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	payerInfo := make_payment.AccountInfo{
		Balance:                            payerAccount.Balance,
		LastAppliedPayerPaymentEventNumber: payerAccount.LastAppliedPayerPaymentEventNumber,
	}
	command := make_payment.NewCommand(req.PayerAccountNumber, req.PayeeAccountNumber, req.Amount, payerInfo)
	err = h.handler.Handle(command)
	if err != nil {
		log.Println(err)
		if errors.Is(err, make_payment.ErrInsufficientFunds) {
			http.Error(w, err.Error(), http.StatusBadRequest)
		} else {
			http.Error(w, err.Error(), http.StatusInternalServerError)
		}
		return
	}

	w.WriteHeader(http.StatusCreated)
}
