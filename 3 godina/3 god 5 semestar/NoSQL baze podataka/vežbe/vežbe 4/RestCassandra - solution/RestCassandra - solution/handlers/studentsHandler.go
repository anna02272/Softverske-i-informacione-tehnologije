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

func (s *StudentsHandler) GetIspitiByStudent(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	studentId := vars["id"]
	
	ispitiByStudent, err := s.repo.GetIspitiByStudent(studentId)
	if err != nil {
		s.logger.Print("Database exception: ", err)
	}

	if ispitiByStudent == nil {
		return
	}

	err = ispitiByStudent.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		s.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (s *StudentsHandler) GetStudentiBySmer(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	smerId := vars["id"]
	
	studentiBySmer, err := s.repo.GetStudentiBySmer(smerId)
	if err != nil {
		s.logger.Print("Database exception: ", err)
	}

	if studentiBySmer == nil {
		return
	}

	err = studentiBySmer.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		s.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (s *StudentsHandler) GetIspitiByPredmetAndSmer(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	predmetId := vars["predmetId"]
	smerId := vars["smerId"]
	
	ispitiByPredmet, err := s.repo.GetIspitiByPredmetAndSmer(predmetId, smerId)
	if err != nil {
		s.logger.Print("Database exception: ", err)
	}

	if ispitiByPredmet == nil {
		return
	}

	err = ispitiByPredmet.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		s.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (s *StudentsHandler) CraeteIspitForStudent(rw http.ResponseWriter, h *http.Request) {
	studentIspit := h.Context().Value(KeyProduct{}).(*data.IspitByStudent)
	err := s.repo.InsertIspitByStudent(studentIspit)
	if err != nil {
		s.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}

func (s *StudentsHandler) CreateIspitForPredmetAndSmer(rw http.ResponseWriter, h *http.Request) {
	predmetSmerIspit := h.Context().Value(KeyProduct{}).(*data.IspitByPredmetAndSmer)
	err := s.repo.InsertIspitByPredmetAndSmer(predmetSmerIspit)
	if err != nil {
		s.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}

func (s *StudentsHandler) CreateStudentForSmer(rw http.ResponseWriter, h *http.Request) {
	studentSmer := h.Context().Value(KeyProduct{}).(*data.StudentBySmer)
	err := s.repo.InsertStudentBySmer(studentSmer)
	if err != nil {
		s.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}

func (s *StudentsHandler) UpdateStudentBySmer(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	smerId := vars["smerId"]
	studentId := vars["studentId"]
	indeks := vars["indeks"]
	
	var stepenStudija string
	d := json.NewDecoder(h.Body)
	d.Decode(&stepenStudija)

	err := s.repo.UpdateIspitByPredmetAddStepenStudija(smerId, studentId, indeks, stepenStudija)
	if err != nil {
		s.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}


func (s *StudentsHandler) MiddlewareOcenaForStudentDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		ispitByStudent := &data.IspitByStudent{}
		err := ispitByStudent.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			s.logger.Fatal(err)
			return
		}
		ctx := context.WithValue(h.Context(), KeyProduct{}, ispitByStudent)
		h = h.WithContext(ctx)
		next.ServeHTTP(rw, h)
	})
}

func (s *StudentsHandler) MiddlewareOcenaForPredmetDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		ispitByPredmetAndSmer := &data.IspitByPredmetAndSmer{}
		err := ispitByPredmetAndSmer.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			s.logger.Fatal(err)
			return
		}
		ctx := context.WithValue(h.Context(), KeyProduct{}, ispitByPredmetAndSmer)
		h = h.WithContext(ctx)
		next.ServeHTTP(rw, h)
	})
}

func (s *StudentsHandler) MiddlewareStudentForSmerDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		studentBySmer := &data.StudentBySmer{}
		err := studentBySmer.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			s.logger.Fatal(err)
			return
		}
		ctx := context.WithValue(h.Context(), KeyProduct{}, studentBySmer)
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
