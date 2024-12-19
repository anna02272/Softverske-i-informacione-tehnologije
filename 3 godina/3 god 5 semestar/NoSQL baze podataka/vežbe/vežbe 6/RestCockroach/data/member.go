package data

import (
	"encoding/json"
	"io"

	"github.com/google/uuid"
)

type Member struct {
	Id         uuid.UUID `gorm:"type:uuid;default:uuid_generate_v4()"`
	Name       string
	Surname    string
	Borrowings []Borrowing
}

type Members []*Member

func (m *Members) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(m)
}

func (m *Member) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(m)
}

func (m *Member) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(m)
}
