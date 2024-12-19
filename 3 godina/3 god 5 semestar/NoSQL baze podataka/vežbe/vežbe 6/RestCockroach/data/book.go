package data

import (
	"encoding/json"
	"io"

	"github.com/google/uuid"
)

type Book struct {
	Isbn        string `gorm:"primaryKey"`
	Genre       string
	Title       string
	IsAvailable bool      `gorm:"default:true"`
	AuthorId    uuid.UUID `gorm:"type:uuid"`
	Author      Author
	Borrowings  []Borrowing `gorm:"foreignKey:BookIsbn;references:Isbn"`
}

type Books []*Book

func (b *Books) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(b)
}

func (b *Book) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(b)
}

func (b *Book) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(b)
}
