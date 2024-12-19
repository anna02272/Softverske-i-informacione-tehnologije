package data

import (
	"encoding/json"
	"io"

	"github.com/google/uuid"
)

type Author struct {
	Id        uuid.UUID `gorm:"type:uuid;default:uuid_generate_v4()"`
	Name      string
	Surname   string
	Biography string
	Books     []Book `gorm:"foreignKey:AuthorId;references:Id"`
}

type Authors []*Author

func (a *Authors) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(a)
}

func (a *Author) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(a)
}

func (a *Author) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(a)
}
