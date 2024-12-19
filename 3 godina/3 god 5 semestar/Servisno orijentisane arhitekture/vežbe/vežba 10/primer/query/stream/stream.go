package stream

import "example/events"

type EventStream interface {
	Process(func(events.Event) error)
}
