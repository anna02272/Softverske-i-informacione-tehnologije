package handlers

import (
	"encoding/json"
	"example/common/messaging"
	"example/profile/data"
	"log"
)

type ProfileEventHandler struct {
	repo *data.ProfilePageViewRepo
}

func NewProfileEventHandler(repo *data.ProfilePageViewRepo, sub messaging.Subscriber) (*ProfileEventHandler, error) {
	handler := &ProfileEventHandler{
		repo: repo,
	}
	err := sub.Subscribe(handler.handle)
	if err != nil {
		return nil, err
	}
	return handler, nil
}

func (handler *ProfileEventHandler) handle(event *messaging.Event) {
	switch event.Type {
	case messaging.ProfileCreated:
		var profile messaging.Profile
		err := json.Unmarshal(event.Payload, &profile)
		if err != nil {
			log.Println(err)
			return
		}
		_, err = handler.repo.Create(profile)
		if err != nil {
			log.Println(err)
		}
	case messaging.ProfileUpdated:
		var profile messaging.Profile
		err := json.Unmarshal(event.Payload, &profile)
		if err != nil {
			log.Println(err)
			return
		}
		_, err = handler.repo.Update(profile)
		if err != nil {
			log.Println(err)
		}
	case messaging.FollowingCreated:
		var following messaging.Following
		err := json.Unmarshal(event.Payload, &following)
		if err != nil {
			log.Println(err)
			return
		}
		_, err = handler.repo.AddFollowing(following)
		if err != nil {
			log.Println(err)
		}
	case messaging.FollowingDeleted:
		var following messaging.Following
		err := json.Unmarshal(event.Payload, &following)
		if err != nil {
			log.Println(err)
			return
		}
		_, err = handler.repo.RemoveFollowing(following)
		if err != nil {
			log.Println(err)
		}
	}
}
