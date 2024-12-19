package handlers

import (
	"encoding/json"
	"example/common/messaging"
	"example/follow/data"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

type FollowingHandler struct {
	repo      *data.FollowingRepository
	publisher messaging.Publisher
}

func NewFollowingHandler(repo *data.FollowingRepository, publisher messaging.Publisher) *FollowingHandler {
	return &FollowingHandler{
		repo:      repo,
		publisher: publisher,
	}
}

func (handler *FollowingHandler) Init(r *mux.Router) {
	r.HandleFunc("/", handler.Create).Methods("POST")
	r.HandleFunc("/", handler.Delete).Methods("DELETE")
	r.HandleFunc("/{id}/followers", handler.GetFollowers).Methods("GET")
	r.HandleFunc("/{id}/follows", handler.GetFollows).Methods("GET")
}

func (handler *FollowingHandler) Create(w http.ResponseWriter, r *http.Request) {
	var follow data.Follow
	err := json.NewDecoder(r.Body).Decode(&follow)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	follow = handler.repo.Create(follow)
	// publish event
	f := messaging.Following{
		FollowerId: follow.Follower.Id,
		FollowId:   follow.Follow.Id,
	}
	eventPayload, err := json.Marshal(f)
	if err != nil {
		log.Println(err)
	}
	event := messaging.Event{
		Type:    messaging.FollowingCreated,
		Payload: eventPayload,
	}
	handler.publisher.Publish(event)

	jsonResponse(follow, w)
}

func (handler *FollowingHandler) Delete(w http.ResponseWriter, r *http.Request) {
	var follow data.Follow
	err := json.NewDecoder(r.Body).Decode(&follow)
	if err != nil {
		log.Println(err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	follow = handler.repo.Delete(follow)
	// publish event
	f := messaging.Following{
		FollowerId: follow.Follower.Id,
		FollowId:   follow.Follow.Id,
	}
	eventPayload, err := json.Marshal(f)
	if err != nil {
		log.Println(err)
	}
	event := messaging.Event{
		Type:    messaging.FollowingDeleted,
		Payload: eventPayload,
	}
	handler.publisher.Publish(event)

	jsonResponse(follow, w)
}

func (handler *FollowingHandler) GetFollowers(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, ok := vars["id"]
	if !ok {
		w.WriteHeader(http.StatusBadRequest)
		return
	}

	profile := data.Profile{Id: id}
	followers := handler.repo.GetFollowers(profile)

	jsonResponse(followers, w)
}

func (handler *FollowingHandler) GetFollows(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, ok := vars["id"]
	if !ok {
		w.WriteHeader(http.StatusBadRequest)
		return
	}

	profile := data.Profile{Id: id}
	follows := handler.repo.GetFollows(profile)

	jsonResponse(follows, w)
}
