package events

import (
	"example/events"
	"example/events/account_created"
	"example/events/payment_made"
	"example/query/domain"
	"log"
)

type EventHandler struct {
	store domain.AccountStore
}

func NewEventHandler(store domain.AccountStore) EventHandler {
	return EventHandler{
		store: store,
	}
}

func (h EventHandler) Handle(event events.Event) error {
	switch e := event.(type) {
	case *account_created.Event:
		account := domain.Account{
			AccountNumber:                      e.AccountNumber,
			HolderName:                         e.HolderName,
			Balance:                            100,
			LastAppliedPayerPaymentEventNumber: -1,
		}
		return h.store.Create(account)
	case *payment_made.Event:
		payer, err := h.store.Read(e.PayerAccountNumber)
		if err != nil {
			return err
		}
		payee, err := h.store.Read(e.PayeeAccountNumber)
		if err != nil {
			return err
		}
		payer.Balance -= e.Amount
		payer.LastAppliedPayerPaymentEventNumber = int64(e.Number())
		payee.Balance += e.Amount
		err = h.store.Update(payer)
		if err != nil {
			log.Println(err)
		}
		err = h.store.Update(payee)
		if err != nil {
			log.Println(err)
		}
	}
	return nil
}
