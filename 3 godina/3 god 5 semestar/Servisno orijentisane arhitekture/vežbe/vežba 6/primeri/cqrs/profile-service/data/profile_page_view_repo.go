package data

import (
	"errors"
	"example/common/messaging"
)

var (
	ErrProfileExists = errors.New("profile already exists")
)

type ProfilePageViewRepo struct {
	profiles map[string]ProfilePageView
}

func NewProfilePageViewRepo(profiles map[string]ProfilePageView) *ProfilePageViewRepo {
	return &ProfilePageViewRepo{
		profiles: profiles,
	}
}

func (repo *ProfilePageViewRepo) Create(profile messaging.Profile) (ProfilePageView, error) {
	_, ok := repo.profiles[profile.Id]
	if ok {
		return ProfilePageView{}, ErrProfileExists
	}
	profileView := ProfilePageView{
		Id:        profile.Id,
		Name:      profile.Name,
		Followers: 0,
		Following: 0,
	}
	repo.profiles[profile.Id] = profileView
	return profileView, nil
}

func (repo *ProfilePageViewRepo) Update(profile messaging.Profile) (ProfilePageView, error) {
	profileView, ok := repo.profiles[profile.Id]
	if !ok {
		return ProfilePageView{}, ErrProfileNotFound
	}
	profileView.Name = profile.Name
	repo.profiles[profile.Id] = profileView
	return profileView, nil
}

func (repo *ProfilePageViewRepo) AddFollowing(following messaging.Following) (messaging.Following, error) {
	follower, ok := repo.profiles[following.FollowerId]
	if !ok {
		return messaging.Following{}, ErrProfileNotFound
	}
	follow, ok := repo.profiles[following.FollowId]
	if !ok {
		return messaging.Following{}, ErrProfileNotFound
	}
	follower.Following++
	repo.profiles[follower.Id] = follower
	follow.Followers++
	repo.profiles[follow.Id] = follow
	return following, nil
}

func (repo *ProfilePageViewRepo) RemoveFollowing(following messaging.Following) (messaging.Following, error) {
	follower, ok := repo.profiles[following.FollowerId]
	if !ok {
		return messaging.Following{}, ErrProfileNotFound
	}
	follow, ok := repo.profiles[following.FollowId]
	if !ok {
		return messaging.Following{}, ErrProfileNotFound
	}
	follower.Following--
	repo.profiles[follower.Id] = follower
	follow.Followers--
	repo.profiles[follow.Id] = follow
	return following, nil
}

func (repo *ProfilePageViewRepo) GetAll() []ProfilePageView {
	profiles := make([]ProfilePageView, 0)
	for _, profile := range repo.profiles {
		profiles = append(profiles, profile)
	}
	return profiles
}

var ProfilePageViews = map[string]ProfilePageView{
	"1": {
		Id:        "1",
		Name:      "Pera Peric",
		Followers: 1,
		Following: 0,
	},
	"2": {
		Id:        "2",
		Name:      "Mika Mikic",
		Followers: 0,
		Following: 1,
	},
}
