package messaging

type Publisher interface {
	Publish(event Event) error
}

type Subscriber interface {
	Subscribe(function func(*Event)) error
}
