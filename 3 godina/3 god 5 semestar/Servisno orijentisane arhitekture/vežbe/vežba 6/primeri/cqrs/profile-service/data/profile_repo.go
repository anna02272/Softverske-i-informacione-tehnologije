package data

import (
	"errors"
	"github.com/google/uuid"
)

var (
	ErrProfileNotFound = errors.New("profile not found")
)

type ProfileRepo struct {
	profiles map[string]Profile
}

func NewProfileRepo(profiles map[string]Profile) *ProfileRepo {
	return &ProfileRepo{
		profiles: profiles,
	}
}

func (repo *ProfileRepo) Create(profile Profile) Profile {
	profile.Id = uuid.NewString()
	repo.profiles[profile.Id] = profile
	return profile
}

func (repo *ProfileRepo) Update(profile Profile) (Profile, error) {
	_, ok := repo.profiles[profile.Id]
	if !ok {
		return Profile{}, ErrProfileNotFound
	}
	repo.profiles[profile.Id] = profile
	return profile, nil
}

func (repo *ProfileRepo) GetAll() []Profile {
	profiles := make([]Profile, 0)
	for _, profile := range repo.profiles {
		profiles = append(profiles, profile)
	}
	return profiles
}

var Profiles = map[string]Profile{
	"1": {
		Id:   "1",
		Name: "Pera Peric",
	},
	"2": {
		Id:   "2",
		Name: "Mika Mikic",
	},
}
