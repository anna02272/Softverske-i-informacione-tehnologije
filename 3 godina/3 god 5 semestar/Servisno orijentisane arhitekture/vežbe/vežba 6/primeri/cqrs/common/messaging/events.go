package messaging

type EventType int8

const (
	ProfileCreated EventType = iota
	ProfileUpdated
	FollowingCreated
	FollowingDeleted
)

type Event struct {
	Type    EventType
	Payload []byte
}

type Profile struct {
	Id   string
	Name string
}

type Following struct {
	FollowerId string
	FollowId   string
}
