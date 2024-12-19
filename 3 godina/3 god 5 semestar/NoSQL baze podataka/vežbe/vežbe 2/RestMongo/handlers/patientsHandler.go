package handlers

import (
	"Rest/data"
	"context"
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

type KeyProduct struct{}

type PatientsHandler struct {
	logger *log.Logger
	// NoSQL: injecting product repository
	repo *data.PatientRepo
}

//Injecting the logger makes this code much more testable.
func NewPatientsHandler(l *log.Logger, r *data.PatientRepo) *PatientsHandler {
	return &PatientsHandler{l, r}
}

func (p *PatientsHandler) GetAllPatients(rw http.ResponseWriter, h *http.Request) {
	patients, err := p.repo.GetAll()
	if err != nil {
		p.logger.Print("Database exception: ", err)
	}

	if patients == nil {
		return
	}

	err = patients.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (p *PatientsHandler) GetPatientById(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id := vars["id"]
	
	patient, err := p.repo.GetById(id)
	if err != nil {
		p.logger.Print("Database exception: ", err)
	}

	if patient == nil {
		http.Error(rw, "Patient with given id not found", http.StatusNotFound)
		p.logger.Printf("Patient with id: '%s' not found", id)
		return
	}

	err = patient.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (p *PatientsHandler) GetPatientsByName(rw http.ResponseWriter, h *http.Request) {
	name := h.URL.Query().Get("name")
	
	patients, err := p.repo.GetByName(name)
	if err != nil {
		p.logger.Print("Database exception: ", err)
	}

	if patients == nil {
		return
	}

	err = patients.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		p.logger.Fatal("Unable to convert to json :", err)
		return
	}
}


func (p *PatientsHandler) PostPatient(rw http.ResponseWriter, h *http.Request) {
	patient := h.Context().Value(KeyProduct{}).(*data.Patient)
	p.repo.Insert(patient)
	rw.WriteHeader(http.StatusCreated)
}

func (p *PatientsHandler) PatchPatient(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id := vars["id"]
	patient := h.Context().Value(KeyProduct{}).(*data.Patient)

	p.repo.Update(id, patient)
	rw.WriteHeader(http.StatusOK)
}

func (p *PatientsHandler) AddPhoneNumber(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id := vars["id"]

	var phoneNumber string
	d := json.NewDecoder(h.Body)
	d.Decode(&phoneNumber)

	p.repo.AddPhoneNumber(id, phoneNumber)
	rw.WriteHeader(http.StatusOK)
}

func (p *PatientsHandler) DeletePatient(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	id := vars["id"]

	p.repo.Delete(id)
	rw.WriteHeader(http.StatusNoContent)
}

func (p *PatientsHandler) MiddlewarePatientDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		patient := &data.Patient{}
		err := patient.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			p.logger.Fatal(err)
			return
		}

		ctx := context.WithValue(h.Context(), KeyProduct{}, patient)
		h = h.WithContext(ctx)

		next.ServeHTTP(rw, h)
	})
}

func (p *PatientsHandler) MiddlewareContentTypeSet(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		p.logger.Println("Method [", h.Method, "] - Hit path :", h.URL.Path)

		rw.Header().Add("Content-Type", "application/json")

		next.ServeHTTP(rw, h)
	})
}