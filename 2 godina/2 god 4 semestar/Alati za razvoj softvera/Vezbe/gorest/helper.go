package main

import (
	"encoding/json"
	"io"
	"net/http"
	ps "github.com/milossimic/gorest/poststore"
	tracer "github.com/milossimic/rest/tracer"
	"context"
)

func decodeBody(ctx context.Context,r io.Reader) (*ps.RequestPost, error) {
	span := tracer.StartSpanFromContext(ctx, "decodeBody")
	defer span.Finish()

	dec := json.NewDecoder(r)
	dec.DisallowUnknownFields()

	var rt ps.RequestPost
	if err := dec.Decode(&rt); err != nil {
		tracer.LogError(span, err)
		return nil, err
	}
	return &rt, nil
}

func renderJSON(ctx context.Context,w http.ResponseWriter, v interface{}) {
	span := tracer.StartSpanFromContext(ctx, "decodeBody")
	defer span.Finish()

	js, err := json.Marshal(v)
	if err != nil {
		tracer.LogError(span, err)
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(js)
}
