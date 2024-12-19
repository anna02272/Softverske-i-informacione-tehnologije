package handlers

import (
	"log"
	"net/http"
	"soa/2023-2024/lab1/microservices/connections-service/services"

	"github.com/gorilla/mux"
)

type ConnectionHandler struct {
	conns services.ConnectionService
}

func NewConnectionHandler(conns services.ConnectionService) (ConnectionHandler, error) {
	return ConnectionHandler{
		conns: conns,
	}, nil
}

func (h ConnectionHandler) Create(w http.ResponseWriter, r *http.Request) {
	req := &struct {
		SourceUserId string
		TargetUserId string
	}{}
	err := readReq(req, r, w)
	if err != nil {
		return
	}

	conn, err := h.conns.Create(r.Context(), req.SourceUserId, req.TargetUserId)
	if err != nil {
		writeErrorResp(err, w)
		return
	}

	resp := struct {
		Users [2]struct {
			Id       string
			Username string
		}
	}{
		Users: [2]struct{ Id, Username string }{
			{
				Id: conn.Users[0].Id.String(),
				// TODO: add username
			},
			{
				Id: conn.Users[1].Id.String(),
				// TODO: add username
			},
		},
	}
	writeResp(resp, http.StatusCreated, w)
}

func (h ConnectionHandler) Delete(w http.ResponseWriter, r *http.Request) {
	req := &struct {
		SourceUserId string
		TargetUserId string
	}{}
	err := readReq(req, r, w)
	if err != nil {
		return
	}

	err = h.conns.Delete(r.Context(), req.SourceUserId, req.TargetUserId)
	if err != nil {
		writeErrorResp(err, w)
		return
	}

	writeResp(nil, http.StatusCreated, w)
}

func (h ConnectionHandler) GetByUser(w http.ResponseWriter, r *http.Request) {
	log.Println("get connections by user")

	userId := mux.Vars(r)["userId"]

	conns, err := h.conns.GetByUser(r.Context(), userId)
	if err != nil {
		writeErrorResp(err, w)
		return
	}

	resp := struct {
		Connections []struct {
			Id       string
			Username string
		}
	}{}
	for _, conn := range conns {
		user := struct {
			Id       string
			Username string
		}{
			Id: conn.Id.String(),
			// TODO: add username
		}
		resp.Connections = append(resp.Connections, user)
	}
	writeResp(resp, http.StatusCreated, w)
}
