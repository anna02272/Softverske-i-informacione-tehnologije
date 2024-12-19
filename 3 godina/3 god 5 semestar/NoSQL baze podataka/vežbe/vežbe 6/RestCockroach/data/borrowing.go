package data

import (
	"encoding/json"
	"io"
	"time"

	"github.com/google/uuid"
)

type Borrowing struct {
	Id           uuid.UUID `gorm:"type:uuid;default:uuid_generate_v4()"`
	BorrowedTime time.Time
	MemberId     uuid.UUID `gorm:"type:uuid"`
	BookIsbn     string
}

type Borrowings []*Borrowing

func (b *Borrowings) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(b)
}

func (b *Borrowing) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(b)
}

func (b *Borrowing) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(b)
}
