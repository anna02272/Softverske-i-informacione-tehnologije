package data

import (
	"encoding/json"
	"io"
)

const (
	globalMeasurement 	= "globalTemp"
	cityMeasurement 	= "cityTemp"
)

type GlobalTempPoint struct {
	// NoSQL: there is no concept of an ID in a traditional sense
	// Data is differentiated by timestamp and series
	// Timestamp can be generated for us by the databse or we can specify it ourselves
	Timestamp				string
	LandAvgTemp 			float64
	LandMaxTemp				float64
	LandMinTemp				float64
}

type CityTempPoint struct {
	Timestamp			string
	AvgTemp 			float64
	City				string
	Country				string
}

type GlobalTempData []GlobalTempPoint
type CityTempData []CityTempPoint


func (d *GlobalTempData) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(d)
}

func (d *CityTempData) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)
	return e.Encode(d)
}

func (p *GlobalTempPoint) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(p)
}

func (p *CityTempPoint) FromJSON(r io.Reader) error {
	d := json.NewDecoder(r)
	return d.Decode(p)
}

func (p *GlobalTempPoint) ToFieldsMap() map[string]interface{} {
	out := make(map[string]interface{})
	out["LandAvgTemp"] = p.LandAvgTemp
	out["LandMaxTemp"] = p.LandMaxTemp
	out["LandMinTemp"] = p.LandMinTemp
	return out
}

func (p *CityTempPoint) ToFieldsMap() map[string]interface{} {
	out := make(map[string]interface{})
	out["AvgTemp"] = p.AvgTemp
	return out
}

func (p *CityTempPoint) ToTagsMap() map[string]string {
	out := make(map[string]string)
	out["City"] = p.City
	out["Country"] = p.Country
	return out
}
