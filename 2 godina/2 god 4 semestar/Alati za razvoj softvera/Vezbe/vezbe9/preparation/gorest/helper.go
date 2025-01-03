package main

import (
	"encoding/json"
	"io"
	"net/http"
	ps "github.com/milossimic/gorest/poststore"
)

func decodeBody(r io.Reader) (*ps.RequestPost, error) {
	dec := json.NewDecoder(r)
	dec.DisallowUnknownFields()

	var rt ps.RequestPost
	if err := dec.Decode(&rt); err != nil {
		return nil, err
	}
	return &rt, nil
}

func renderJSON(w http.ResponseWriter, v interface{}) {
	js, err := json.Marshal(v)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(js)
}
