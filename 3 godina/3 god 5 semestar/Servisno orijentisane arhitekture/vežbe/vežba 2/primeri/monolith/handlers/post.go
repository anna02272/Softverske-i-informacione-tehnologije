package handlers

import (
	"net/http"
	"soa/2023-2024/lab1/monolith/services"

	"github.com/gorilla/mux"
)

type PostHandler struct {
	posts services.PostService
}

func NewPostHandler(posts services.PostService) (PostHandler, error) {
	return PostHandler{
		posts: posts,
	}, nil
}

func (h PostHandler) Create(w http.ResponseWriter, r *http.Request) {
	req := &struct {
		OwnerId string
		Content string
	}{}
	err := readReq(req, r, w)
	if err != nil {
		return
	}

	post, err := h.posts.Create(r.Context(), req.OwnerId, req.Content)
	if err != nil {
		writeErrorResp(err, w)
		return
	}

	resp := struct {
		Id      string
		Owner   string
		Content string
	}{
		Id:      post.Id.String(),
		Owner:   post.Owner.Username,
		Content: post.Content,
	}
	writeResp(resp, http.StatusCreated, w)
}

func (h PostHandler) Like(w http.ResponseWriter, r *http.Request) {
	req := &struct {
		UserId string
	}{}
	err := readReq(req, r, w)
	if err != nil {
		return
	}
	postId := mux.Vars(r)["postId"]

	post, err := h.posts.Like(r.Context(), postId, req.UserId)
	if err != nil {
		writeErrorResp(err, w)
		return
	}

	resp := struct {
		Id      string
		Owner   string
		Content string
		Likes   []string
	}{
		Id:      post.Id.String(),
		Owner:   post.Owner.Username,
		Content: post.Content,
	}
	for _, like := range post.Likes {
		resp.Likes = append(resp.Likes, like.Username)
	}
	writeResp(resp, http.StatusCreated, w)
}

func (h PostHandler) GetHomeFeed(w http.ResponseWriter, r *http.Request) {
	userId := mux.Vars(r)["userId"]

	posts, err := h.posts.GetHomeFeed(r.Context(), userId)
	if err != nil {
		writeErrorResp(err, w)
		return
	}

	resp := struct {
		Posts []struct {
			Content string
			Likes   []string
		}
	}{}
	for _, post := range posts {
		respPost := struct {
			Content string
			Likes   []string
		}{Content: post.Content}
		for _, like := range post.Likes {
			respPost.Likes = append(respPost.Likes, like.Username)
		}
		resp.Posts = append(resp.Posts, respPost)
	}

	writeResp(resp, http.StatusOK, w)
}
