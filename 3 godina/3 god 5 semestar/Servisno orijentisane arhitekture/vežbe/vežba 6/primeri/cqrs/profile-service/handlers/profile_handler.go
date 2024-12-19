package handlers

import (
	"encoding/json"
	"example/common/messaging"
	"example/profile/data"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

type ProfileHandler struct {
	repo         *data.ProfileRepo
	pageViewRepo *data.ProfilePageViewRepo
	publisher    messaging.Publisher
}

func NewProfileHandler(repo *data.ProfileRepo, pageViewRepo *data.ProfilePageViewRepo, publisher messaging.Publisher) *ProfileHandler {
	return &ProfileHandler{
		repo:         repo,
		pageViewRepo: pageViewRepo,
		publisher:    publisher,
	}
}

func (handler *ProfileHandler) Init(r *mux.Router) {
	r.HandleFunc("/", handler.Create).Methods("POST")
	r.HandleFunc("/", handler.Update).Methods("PUT")
	r.HandleFunc("/pageview", handler.GetPageViewAll).Methods("GET")
	r.HandleFunc("/", handler.GetAll).Methods("GET")
}

func (handler *ProfileHandler) Create(w http.ResponseWriter, r *http.Request) {
	var profile data.Profile
	err := json.NewDecoder(r.Body).Decode(&profile)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	profile = handler.repo.Create(profile)
	// publish event
	eventPayload, err := json.Marshal(profile)
	if err != nil {
		log.Println(err)
	}
	event := messaging.Event{
		Type:    messaging.ProfileCreated,
		Payload: eventPayload,
	}
	handler.publisher.Publish(event)

	jsonResponse(profile, w)
}

func (handler *ProfileHandler) Update(w http.ResponseWriter, r *http.Request) {
	var profile data.Profile
	err := json.NewDecoder(r.Body).Decode(&profile)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	profile, err = handler.repo.Update(profile)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}

	// publish event
	eventPayload, err := json.Marshal(profile)
	if err != nil {
		log.Println(err)
	}
	event := messaging.Event{
		Type:    messaging.ProfileUpdated,
		Payload: eventPayload,
	}
	handler.publisher.Publish(event)

	jsonResponse(profile, w)
}

func (handler *ProfileHandler) GetAll(w http.ResponseWriter, r *http.Request) {
	profiles := handler.repo.GetAll()
	jsonResponse(profiles, w)
}

func (handler *ProfileHandler) GetPageViewAll(w http.ResponseWriter, r *http.Request) {
	profiles := handler.pageViewRepo.GetAll()
	jsonResponse(profiles, w)
}
