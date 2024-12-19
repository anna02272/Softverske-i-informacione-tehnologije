package data

type FollowingRepository struct {
	followers, follows map[Profile][]Profile
}

func NewFollowingRepository(followers, follows map[Profile][]Profile) *FollowingRepository {
	return &FollowingRepository{
		followers: followers,
		follows:   follows,
	}
}

func (repo *FollowingRepository) Create(follow Follow) Follow {
	repo.followers[follow.Follow] = append(repo.followers[follow.Follow], follow.Follower)
	repo.follows[follow.Follower] = append(repo.follows[follow.Follower], follow.Follow)
	return follow
}

func (repo *FollowingRepository) Delete(follow Follow) Follow {
	followers := repo.followers[follow.Follow]
	followers = remove(followers, follow.Follower)
	repo.followers[follow.Follow] = followers
	follows := repo.follows[follow.Follower]
	follows = remove(follows, follow.Follow)
	repo.follows[follow.Follower] = follows
	return follow
}

func (repo *FollowingRepository) GetFollowers(profile Profile) []Profile {
	return repo.followers[profile]
}

func (repo *FollowingRepository) GetFollows(profile Profile) []Profile {
	return repo.follows[profile]
}

func remove(profiles []Profile, profile Profile) []Profile {
	index := -1
	for i, p := range profiles {
		if p == profile {
			index = i
			break
		}
	}
	if index == -1 {
		return profiles
	}
	return append(profiles[:index], profiles[index+1:]...)
}

var Followers = map[Profile][]Profile{
	Profile{Id: "1"}: {Profile{Id: "2"}},
}

var Follows = map[Profile][]Profile{
	Profile{Id: "2"}: {Profile{Id: "1"}},
}
