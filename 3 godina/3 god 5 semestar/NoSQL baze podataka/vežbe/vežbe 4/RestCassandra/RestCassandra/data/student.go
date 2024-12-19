package data

import (
	"encoding/json"
	"io"
	"time"

	"github.com/gocql/gocql"
)

type OcenaByStudent struct {
	StudentId   gocql.UUID
	Ime        	string
	Prezime		string
	Indeks		string
	Ocena		int
	Datum		string
	Id			gocql.UUID
	Predmet		string
}

type OcenaByPredmet struct {
	PredmetId   gocql.UUID
	Indeks		string
	Ocena		int
	DatumId		time.Time
	Naziv       string
	Ime			string
	Prezime		string
}


type OceneByStudent []*OcenaByStudent

type OceneByPredmet []*OcenaByPredmet

func (o *OceneByStudent) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(o)
}

func (o *OceneByPredmet) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(o)
}

func (o *OcenaByStudent) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(o)
}

func (o *OcenaByPredmet) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(o)
}
