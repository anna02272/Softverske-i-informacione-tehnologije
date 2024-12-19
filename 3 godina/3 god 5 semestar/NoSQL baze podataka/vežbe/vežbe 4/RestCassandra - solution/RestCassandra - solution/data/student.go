package data

import (
	"encoding/json"
	"io"
	"time"

	"github.com/gocql/gocql"
)

type IspitByStudent struct {
	StudentId   gocql.UUID
	Ime        	string
	Prezime		string
	Indeks		string
	Ocena		int
	DatumId		time.Time
	Predmet		string
}

/*
type IspitByPredmet struct {
	PredmetId   	gocql.UUID
	Indeks			string
	Ocena			int
	IspitId			gocql.UUID
	Datum			string
	Naziv       	string
	Ime				string
	Prezime			string
}
*/
// Zadatak 2: Prikaz ocena za predmet i smer (dopunjena prethodna tabela)
// ispiti_by_predmet_and_smer
// PK: predmet_id, smer_id
// CK: ocena DESC
// indeks, ime, prezime, predmet_naziv, smer_naziv
type IspitByPredmetAndSmer struct {
	PredmetId 		gocql.UUID
	SmerId 			gocql.UUID
	Indeks 			string
	Ocena 			int
	IspitId			gocql.UUID
	Datum			string
	Ime 			string
	Prezime 		string
	PredmetNaziv 	string
	SmerNaziv 		string
}

// Zadatak 1: Prikaz studenata u okviru studijskog programa (smer)
// studenti_by_smer
// PK: smer_id
// CK: indeks ASC, student_id ASC
// ime, prezime, smer_naziv
type StudentBySmer struct {
	SmerId 		gocql.UUID
	StudentId	gocql.UUID
	Indeks 		string
	Ime 		string
	Prezime 	string
	SmerNaziv 	string
	// Zadatak 3: Student sadrzi informaciju o zavrsenim stepenima studija
	// (mozemo dopuniti svaku tabelu koja sadrzi informaciju o studentu ako nam je bitno da prikazemo stepeni studija u okviru UseCase-a)
	StepeniStudija	[]string
}

type IspitiByStudent []*IspitByStudent
type IspitiByPredmetAndSmer []*IspitByPredmetAndSmer
type StudentiBySmer []*StudentBySmer

func (o *IspitiByStudent) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(o)
}
func (o *IspitiByPredmetAndSmer) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(o)
}
func (o *StudentiBySmer) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(o)
}

func (o *IspitByStudent) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(o)
}
func (o *IspitByPredmetAndSmer) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(o)
}
func (o *StudentBySmer) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(o)
}
