package data

type Profile struct {
	Id string
}

type Follow struct {
	Follower, Follow Profile
}
