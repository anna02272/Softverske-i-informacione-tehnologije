package poststore

import (
	"github.com/hashicorp/consul/api"
	"os"
	"fmt"
	"encoding/json"
	tracer "github.com/milossimic/gorest/tracer"
	"context"
)

type PostStore struct {
	cli *api.Client
}

func New() (*PostStore, error) {
	db := os.Getenv("DB")
	dbport := os.Getenv("DBPORT")

	config := api.DefaultConfig()
	config.Address = fmt.Sprintf("%s:%s", db, dbport)
	client, err := api.NewClient(config)
	if err != nil {
		return nil, err
	}

	return &PostStore{
		cli: client,
	}, nil
}

func (ps *PostStore) Get(ctx context.Context,id string) (*RequestPost, error) {
	span := tracer.StartSpanFromContext(ctx, "Get")
	defer span.Finish()

	kv := ps.cli.KV()

	pair, _, err := kv.Get(constructKey(id), nil)
	if err != nil {
		tracer.LogError(span, err)
		return nil, err
	}

	post := &RequestPost{}
	err = json.Unmarshal(pair.Value, post)
	if err != nil {
		tracer.LogError(span, err)
		return nil, err
	}

	return post, nil
}

func (ps *PostStore) GetAll(ctx context.Context) ([]*RequestPost, error) {
	span := tracer.StartSpanFromContext(ctx, "GetAll")
	defer span.Finish()

	kv := ps.cli.KV()
	data, _, err := kv.List(all, nil)
	if err != nil {
		tracer.LogError(span, err)
		return nil, err
	}

	posts := []*RequestPost{}
	for _, pair := range data {
		post := &RequestPost{}
		err = json.Unmarshal(pair.Value, post)
		if err != nil {
			tracer.LogError(span, err)
			return nil, err
		}
		posts = append(posts, post)
	}

	return posts, nil
}

func (ps *PostStore) Delete(ctx context.Context,id string) (map[string]string, error) {
	span := tracer.StartSpanFromContext(ctx, "Delete")
	defer span.Finish()

	kv := ps.cli.KV()
	_, err := kv.Delete(constructKey(id), nil)
	if err != nil {
		tracer.LogError(span, err)
		return nil, err
	}

	return map[string]string{"Deleted":id}, nil
}

func (ps *PostStore) Post(ctx context.Context,post *RequestPost) (*RequestPost, error) {
	span := tracer.StartSpanFromContext(ctx, "Post")
	defer span.Finish()

	kv := ps.cli.KV()

	sid, rid := generateKey()
	post.Id = rid

	data, err := json.Marshal(post)
	if err != nil {
		tracer.LogError(span, err)
		return nil, err
	}

	p := &api.KVPair{Key: sid, Value: data}
	_, err = kv.Put(p, nil)
	if err != nil {
		tracer.LogError(span, err)
		return nil, err
	}

	return post, nil
}
