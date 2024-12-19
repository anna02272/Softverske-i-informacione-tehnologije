package handlers

import (
	"Rest/data"
	"context"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

type KeyProduct struct{}

type TempHandler struct {
	logger *log.Logger
	// NoSQL: injecting temperature repository
	repo *data.TempRepo
}

//Injecting the logger makes this code much more testable.
func NewTempHandler(l *log.Logger, r *data.TempRepo) *TempHandler {
	return &TempHandler{l, r}
}

func (t *TempHandler) GetGlobalPointsInPastTime(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	period := vars["period"]

	globalData, err := t.repo.GetGlobalPointsInPastTime(period)

	if err != nil {
		t.logger.Print("Database exception: ", err)
	}

	if globalData == nil {
		return
	}

	err = globalData.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		t.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (t *TempHandler) GetCityPointsInPastTime(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	period := vars["period"]

	cityData, err := t.repo.GetCityPointsInPastTime(period)

	if err != nil {
		t.logger.Print("Database exception: ", err)
	}

	if cityData == nil {
		return
	}

	err = cityData.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		t.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (t *TempHandler) PostGlobalPoint(rw http.ResponseWriter, h *http.Request) {
	globalPoint := h.Context().Value(KeyProduct{}).(*data.GlobalTempPoint)
	t.repo.WriteGlobalTempPoint(globalPoint)
	rw.WriteHeader(http.StatusCreated)
}

func (t *TempHandler) PostCityPoint(rw http.ResponseWriter, h *http.Request) {
	cityPoint := h.Context().Value(KeyProduct{}).(*data.CityTempPoint)
	t.repo.WriteCityTempPoint(cityPoint)
	rw.WriteHeader(http.StatusCreated)
}


func (t *TempHandler) MiddlewareGlobalPointDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		globalPoint := &data.GlobalTempPoint{}
		err := globalPoint.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			t.logger.Fatal(err)
			return
		}

		ctx := context.WithValue(h.Context(), KeyProduct{}, globalPoint)
		h = h.WithContext(ctx)

		next.ServeHTTP(rw, h)
	})
}

func (t *TempHandler) MiddlewareCityPointDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		cityPoint := &data.CityTempPoint{}
		err := cityPoint.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			t.logger.Fatal(err)
			return
		}

		ctx := context.WithValue(h.Context(), KeyProduct{}, cityPoint)
		h = h.WithContext(ctx)

		next.ServeHTTP(rw, h)
	})
}

func (t *TempHandler) MiddlewareContentTypeSet(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		t.logger.Println("Method [", h.Method, "] - Hit path :", h.URL.Path)

		rw.Header().Add("Content-Type", "application/json")

		next.ServeHTTP(rw, h)
	})
}
