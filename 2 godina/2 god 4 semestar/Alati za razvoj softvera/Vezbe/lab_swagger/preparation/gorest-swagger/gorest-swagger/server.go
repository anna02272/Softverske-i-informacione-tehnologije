package main

import (
	"errors"
	"mime"
	"net/http"

	"github.com/gorilla/mux"
)

type postServer struct {
	data map[string]*RequestPost // izigrava bazu podataka
}

// swagger:route POST /post/ post createPost
// Add new post
//
// responses:
//  415: ErrorResponse
//  400: ErrorResponse
//  201: ResponsePost
func (ts *postServer) createPostHandler(w http.ResponseWriter, req *http.Request) {
	contentType := req.Header.Get("Content-Type")
	mediatype, _, err := mime.ParseMediaType(contentType)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	if mediatype != "application/json" {
		err := errors.New("Expect application/json Content-Type")
		http.Error(w, err.Error(), http.StatusUnsupportedMediaType)
		return
	}

	rt, err := decodeBody(req.Body)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	id := createId()
	rt.Id = id
	ts.data[id] = rt
	renderJSON(w, rt)
}

// swagger:route GET /posts/ post getPosts
// Get all posts
//
// responses:
//  200: []ResponsePost
func (ts *postServer) getAllHandler(w http.ResponseWriter, req *http.Request) {
	allTasks := []*RequestPost{}
	for _, v := range ts.data {
		allTasks = append(allTasks, v)
	}

	renderJSON(w, allTasks)
}

// swagger:route GET /post/{id}/ post getPostById
// Get post by ID
//
// responses:
//  404: ErrorResponse
//  200: ResponsePost
func (ts *postServer) getPostHandler(w http.ResponseWriter, req *http.Request) {
	id := mux.Vars(req)["id"]
	task, ok := ts.data[id]
	if !ok {
		err := errors.New("key not found")
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}
	renderJSON(w, task)
}

// swagger:route DELETE /post/{id}/ post deletePost
// Delete post
//
// responses:
//  404: ErrorResponse
//  204: NoContentResponse
func (ts *postServer) delPostHandler(w http.ResponseWriter, req *http.Request) {
	id := mux.Vars(req)["id"]
	if _, ok := ts.data[id]; ok {
		delete(ts.data, id)
		w.WriteHeader(http.StatusNoContent)
	} else {
		err := errors.New("key not found")
		http.Error(w, err.Error(), http.StatusNotFound)
	}
}

func (ts *postServer) swaggerHandler(w http.ResponseWriter, r *http.Request) {
	http.ServeFile(w, r, "./swagger.yaml")
}