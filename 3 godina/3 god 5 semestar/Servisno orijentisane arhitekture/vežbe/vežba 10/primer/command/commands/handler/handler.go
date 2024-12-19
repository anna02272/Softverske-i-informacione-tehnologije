package handler

import (
	"errors"
	"example/command/commands"
	"example/command/commands/create_account"
	"example/command/commands/make_payment"
	"example/command/store"
	"example/events"
	"example/events/account_created"
	"example/events/payment_made"
	"github.com/google/uuid"
)

type Handler struct {
	store store.EventStore
}

func NewHandler(store store.EventStore) Handler {
	return Handler{store: store}
}

func (h Handler) Handle(command commands.Command) error {
	event, err := h.execute(command)
	if err != nil {
		return err
	}

	eventJson, err := event.ToJSON()
	if err != nil {
		return err
	}
	if event.ExpectedLastEventNumber() >= 0 {
		// we want to enforce event order check
		return h.store.StoreAndExpectLastEventNumber(
			event.Stream(),
			event.Type(),
			eventJson,
			uint64(event.ExpectedLastEventNumber()))
	} else {
		return h.store.Store(event.Stream(), event.Type(), eventJson)
	}
}

func (h Handler) execute(command commands.Command) (event events.Event, err error) {
	switch c := command.(type) {
	case *create_account.CreateAccountCommand:
		event, err = h.createAccount(c)
	case *make_payment.MakePaymentCommand:
		event, err = h.makePayment(c)
	default:
		err = errors.New("unknown command")
	}
	return
}

func (h Handler) createAccount(command *create_account.CreateAccountCommand) (events.Event, error) {
	accountNumber := uuid.NewString()
	return account_created.NewEvent(
			accountNumber,
			command.HolderName,
			-1),
		nil
}

func (h Handler) makePayment(command *make_payment.MakePaymentCommand) (events.Event, error) {
	if command.Amount > command.PayerAccount.Balance {
		return nil, make_payment.ErrInsufficientFunds
	}
	return payment_made.NewEvent(
			command.PayerAccountNumber,
			command.PayeeAccountNumber,
			command.Amount,
			command.PayerAccount.LastAppliedPayerPaymentEventNumber),
		nil
}
