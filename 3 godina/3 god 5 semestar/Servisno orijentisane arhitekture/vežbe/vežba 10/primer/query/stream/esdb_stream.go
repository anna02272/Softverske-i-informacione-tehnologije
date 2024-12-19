package stream

import (
	"context"
	"example/events"
	"example/events/account_created"
	"example/events/payment_made"
	"github.com/EventStore/EventStore-Client-Go/esdb"
	"log"
)

type ESDBEventStream struct {
	client *esdb.Client
	group  string
	sub    *esdb.PersistentSubscription
}

func NewESDBEventStream(client *esdb.Client, group string) (EventStream, error) {
	opts := esdb.PersistentAllSubscriptionOptions{
		From: esdb.Start{},
	}
	err := client.CreatePersistentSubscriptionAll(context.Background(), group, opts)
	if err != nil {
		// persistent subscription group already exists
		log.Println(err)
	}
	eventStream := &ESDBEventStream{
		client: client,
		group:  group,
	}
	err = eventStream.subscribe()
	if err != nil {
		log.Println(err)
		return nil, err
	}
	return eventStream, nil
}

func (s *ESDBEventStream) Process(processFn func(events.Event) error) {
	for {
		e := s.sub.Recv()

		if e.EventAppeared != nil {
			streamEvent := e.EventAppeared.Event
			var event events.Event
			switch streamEvent.EventType {
			case events.EventTypeAccountCreated:
				event = account_created.NewEmptyEvent()
			case events.EventTypePaymentMade:
				event = payment_made.NewEmptyEvent()
			}
			if event == nil {
				log.Println("unknown event type")
				continue
			}
			event.SetNumber(streamEvent.EventNumber)
			err := event.FromJSON(streamEvent.Data)
			err = processFn(event)
			if err != nil {
				log.Println(err)
				s.sub.Nack(err.Error(), esdb.Nack_Retry, e.EventAppeared)
			} else {
				s.sub.Ack(e.EventAppeared)
			}

		}

		if e.SubscriptionDropped != nil {
			log.Println(e.SubscriptionDropped.Error)
			// retry subscription
			for err := s.subscribe(); err != nil; {
			}
		}
	}
}

func (s *ESDBEventStream) subscribe() error {
	opts := esdb.ConnectToPersistentSubscriptionOptions{}
	sub, err := s.client.ConnectToPersistentSubscriptionToAll(context.Background(), s.group, opts)
	if err != nil {
		return err
	}
	s.sub = sub
	return nil
}
