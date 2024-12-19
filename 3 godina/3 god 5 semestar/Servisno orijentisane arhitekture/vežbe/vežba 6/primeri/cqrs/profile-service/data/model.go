package data

type Profile struct {
	Id   string
	Name string
}

type ProfilePageView struct {
	Id        string
	Name      string
	Followers uint32
	Following uint32
}
