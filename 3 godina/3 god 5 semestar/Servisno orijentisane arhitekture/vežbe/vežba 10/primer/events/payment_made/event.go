package payment_made

import (
	"encoding/json"
	"example/events"
)

type Event struct {
	PayerAccountNumber string
	PayeeAccountNumber string
	Amount             uint32
	// number is not stored in the event store
	// we only use it to implement optimistic concurrency control
	number                  uint64
	expectedLastEventNumber int64
}

func NewEvent(payerAccountNumber, payeeAccountNumber string, amount uint32, expectedLastEventNumber int64) events.Event {
	return &Event{
		PayerAccountNumber:      payerAccountNumber,
		PayeeAccountNumber:      payeeAccountNumber,
		Amount:                  amount,
		expectedLastEventNumber: expectedLastEventNumber,
	}
}

func NewEmptyEvent() events.Event {
	return &Event{}
}

func (e *Event) Type() string {
	return events.EventTypePaymentMade
}

func (e *Event) ToJSON() ([]byte, error) {
	return json.Marshal(e)
}

func (e *Event) FromJSON(jsonEvent []byte) error {
	return json.Unmarshal(jsonEvent, e)
}

func (e *Event) Number() uint64 {
	return e.number
}

func (e *Event) SetNumber(number uint64) {
	e.number = number
}

func (e *Event) Stream() string {
	return "payment_made" + e.PayerAccountNumber
}

func (e *Event) ExpectedLastEventNumber() int64 {
	return e.expectedLastEventNumber
}

func (e *Event) SetExpectedLastEventNumber(number uint64) {
	e.expectedLastEventNumber = int64(number)
}
