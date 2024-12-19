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

type StudentsHandler struct {
	logger *log.Logger
	// NoSQL: injecting student repository
	repo *data.StudentRepo
}

//Injecting the logger makes this code much more testable.
func NewStudentsHandler(l *log.Logger, r *data.StudentRepo) *StudentsHandler {
	return &StudentsHandler{l, r}
}

func (s *StudentsHandler) GetAllStudentIds(rw http.ResponseWriter, h *http.Request) {
	studentIds, err := s.repo.GetDistinctIds("student_id", "ocene_by_student")
	if err != nil {
		s.logger.Print("Database exception: ", err)
	}

	if studentIds == nil {
		return
	}

	s.logger.Println(studentIds)

	e := json.NewEncoder(rw)
	err = e.Encode(studentIds)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		s.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (s *StudentsHandler) GetAllPredmetIds(rw http.ResponseWriter, h *http.Request) {
	predmetIds, err := s.repo.GetDistinctIds("predmet_id", "ocene_by_predmet")
	if err != nil {
		s.logger.Print("Database exception: ", err)
	}

	if predmetIds == nil {
		return
	}

	s.logger.Println(predmetIds)

	e := json.NewEncoder(rw)
	err = e.Encode(predmetIds)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		s.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (s *StudentsHandler) GetOceneByStudent(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	studentId := vars["id"]
	
	oceneByStudent, err := s.repo.GetOceneByStudent(studentId)
	if err != nil {
		s.logger.Print("Database exception: ", err)
	}

	if oceneByStudent == nil {
		return
	}

	err = oceneByStudent.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		s.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (s *StudentsHandler) GetOceneByPredmet(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	predmetId := vars["id"]
	
	oceneByPredmet, err := s.repo.GetOceneByPredmet(predmetId)
	if err != nil {
		s.logger.Print("Database exception: ", err)
	}

	if oceneByPredmet == nil {
		return
	}

	err = oceneByPredmet.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		s.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (s *StudentsHandler) CraeteOcenaForStudent(rw http.ResponseWriter, h *http.Request) {
	studentOcena := h.Context().Value(KeyProduct{}).(*data.OcenaByStudent)
	err := s.repo.InsertOcenaByStudent(studentOcena)
	if err != nil {
		s.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}

func (s *StudentsHandler) CreateOcenaForPredmet(rw http.ResponseWriter, h *http.Request) {
	predmetOcena := h.Context().Value(KeyProduct{}).(*data.OcenaByPredmet)
	err := s.repo.InsertOcenaByPredmet(predmetOcena)
	if err != nil {
		s.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}


func (s *StudentsHandler) MiddlewareOcenaForStudentDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		patient := &data.OcenaByStudent{}
		err := patient.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			s.logger.Fatal(err)
			return
		}
		ctx := context.WithValue(h.Context(), KeyProduct{}, patient)
		h = h.WithContext(ctx)
		next.ServeHTTP(rw, h)
	})
}

func (s *StudentsHandler) MiddlewareOcenaForPredmetDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		patient := &data.OcenaByPredmet{}
		err := patient.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			s.logger.Fatal(err)
			return
		}
		ctx := context.WithValue(h.Context(), KeyProduct{}, patient)
		h = h.WithContext(ctx)
		next.ServeHTTP(rw, h)
	})
}

func (s *StudentsHandler) MiddlewareContentTypeSet(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		s.logger.Println("Method [", h.Method, "] - Hit path :", h.URL.Path)

		rw.Header().Add("Content-Type", "application/json")

		next.ServeHTTP(rw, h)
	})
}
