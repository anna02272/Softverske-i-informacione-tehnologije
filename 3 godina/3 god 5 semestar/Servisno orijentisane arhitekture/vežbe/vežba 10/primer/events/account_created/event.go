package account_created

import (
	"encoding/json"
	"example/events"
)

type Event struct {
	AccountNumber string
	HolderName    string
	// number is not stored in the event store
	// we only use it to implement optimistic concurrency control
	number                  uint64
	expectedLastEventNumber int64
}

func NewEvent(accountNumber, holderName string, expectedLastEventNumber int64) events.Event {
	return &Event{
		AccountNumber:           accountNumber,
		HolderName:              holderName,
		expectedLastEventNumber: expectedLastEventNumber,
	}
}

func NewEmptyEvent() events.Event {
	return &Event{}
}

func (e *Event) Type() string {
	return events.EventTypeAccountCreated
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
	return "account_created"
}

func (e *Event) ExpectedLastEventNumber() int64 {
	return e.expectedLastEventNumber
}

func (e *Event) SetExpectedLastEventNumber(number uint64) {
	e.expectedLastEventNumber = int64(number)
}
