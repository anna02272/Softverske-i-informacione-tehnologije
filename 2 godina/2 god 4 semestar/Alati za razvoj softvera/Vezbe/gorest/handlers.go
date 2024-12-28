package main

import (
	"context"
	"fmt"
	"github.com/gorilla/mux"
	tracer "github.com/milossimic/gorest/tracer"
	"mime"
	"net/http"
	"errors"
)

func (ts *postServer) createPostHandler(w http.ResponseWriter, req *http.Request) {
	span := tracer.StartSpanFromRequest("cretePostHandler", ts.tracer, req)
	defer span.Finish()

	span.LogFields(
		tracer.LogString("handler", fmt.Sprintf("handling post create at %s\n", req.URL.Path)),
	)

	contentType := req.Header.Get("Content-Type")
	mediatype, _, err := mime.ParseMediaType(contentType)
	if err != nil {
		tracer.LogError(span, err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	if mediatype != "application/json" {
		err := errors.New("Expect application/json Content-Type")
		http.Error(w, err.Error(), http.StatusUnsupportedMediaType)
		return
	}

	ctx := tracer.ContextWithSpan(context.Background(), span)
	rt, err := decodeBody(ctx, req.Body)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	post, err := ts.store.Post(ctx, rt)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	renderJSON(ctx, w, post)
}

func (ts *postServer) getAllHandler(w http.ResponseWriter, req *http.Request) {
	span := tracer.StartSpanFromRequest("getAllPostsHandler", ts.tracer, req)
	defer span.Finish()

	span.LogFields(
		tracer.LogString("handler", fmt.Sprintf("handling get all posts at %s\n", req.URL.Path)),
	)

	ctx := tracer.ContextWithSpan(context.Background(), span)
	allTasks, err := ts.store.GetAll(ctx)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	renderJSON(ctx, w, allTasks)
}

func (ts *postServer) getPostHandler(w http.ResponseWriter, req *http.Request) {
	span := tracer.StartSpanFromRequest("getPostHandler", ts.tracer, req)
	defer span.Finish()

	span.LogFields(
		tracer.LogString("handler", fmt.Sprintf("handling get all posts at %s\n", req.URL.Path)),
	)

	ctx := tracer.ContextWithSpan(context.Background(), span)
	id := mux.Vars(req)["id"]
	task, err := ts.store.Get(ctx, id)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	renderJSON(ctx, w, task)
}

func (ts *postServer) delPostHandler(w http.ResponseWriter, req *http.Request) {
	span := tracer.StartSpanFromRequest("deletePostHandler", ts.tracer, req)
	defer span.Finish()

	span.LogFields(
		tracer.LogString("handler", fmt.Sprintf("handling delete post at %s\n", req.URL.Path)),
	)

	ctx := tracer.ContextWithSpan(context.Background(), span)
	id := mux.Vars(req)["id"]
	msg, err := ts.store.Delete(ctx, id)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	renderJSON(ctx, w, msg)
}
