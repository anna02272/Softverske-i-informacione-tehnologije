package handlers

import (
	"Rest/data"
	"context"
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

type KeyProduct struct{}

type LibraryHandler struct {
	logger *log.Logger
	// NoSQL: injecting student repository
	repo *data.LibraryRepo
}

// Injecting the logger makes this code much more testable.
func NewLibraryHandler(l *log.Logger, r *data.LibraryRepo) *LibraryHandler {
	return &LibraryHandler{l, r}
}

// TRI JOIN-a
// dobavi sve knjige iz biblioteke sa autorima
// dobavi sve pozjamljenice za clana
// dobavi sve nesto???

// demo jedan join

func (l *LibraryHandler) CreateAuthor(rw http.ResponseWriter, h *http.Request) {
	author := h.Context().Value(KeyProduct{}).(*data.Author)
	err := l.repo.InsertAuthor(author)
	if err != nil {
		l.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}

func (l *LibraryHandler) GetAuthors(rw http.ResponseWriter, h *http.Request) {
	authors, err := l.repo.GetAuthors()
	if err != nil {
		l.logger.Print("Database exception: ", err)
	}

	if authors == nil {
		return
	}

	e := json.NewEncoder(rw)
	err = e.Encode(authors)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		l.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (l *LibraryHandler) CreateBook(rw http.ResponseWriter, h *http.Request) {
	book := h.Context().Value(KeyProduct{}).(*data.Book)
	err := l.repo.InsertBook(book)
	if err != nil {
		l.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}

func (l *LibraryHandler) GetBookByIsbn(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	isbn := vars["isbn"]

	book, err := l.repo.GetBookByIsbn(isbn)
	if err != nil {
		l.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}

	if book == nil {
		http.Error(rw, "Book with given ISBN not found", http.StatusNotFound)
		l.logger.Printf("Book with ISBN: '%s' not found", isbn)
		return
	}

	err = book.ToJSON(rw)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		l.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (l *LibraryHandler) GetBooks(rw http.ResponseWriter, h *http.Request) {
	books, err := l.repo.GetBooks()
	if err != nil {
		l.logger.Print("Database exception: ", err)
	}

	if books == nil {
		return
	}

	e := json.NewEncoder(rw)
	err = e.Encode(books)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		l.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (l *LibraryHandler) CreateMember(rw http.ResponseWriter, h *http.Request) {
	member := h.Context().Value(KeyProduct{}).(*data.Member)
	err := l.repo.InsertMember(member)
	if err != nil {
		l.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}

func (l *LibraryHandler) GetMembers(rw http.ResponseWriter, h *http.Request) {
	members, err := l.repo.GetMembers()
	if err != nil {
		l.logger.Print("Database exception: ", err)
	}

	if members == nil {
		return
	}

	e := json.NewEncoder(rw)
	err = e.Encode(members)
	if err != nil {
		http.Error(rw, "Unable to convert to json", http.StatusInternalServerError)
		l.logger.Fatal("Unable to convert to json :", err)
		return
	}
}

func (l *LibraryHandler) BorrowBook(rw http.ResponseWriter, h *http.Request) {
	vars := mux.Vars(h)
	isbn := vars["isbn"]
	memberId := vars["memberId"]
	err := l.repo.BorrowBook(isbn, memberId)
	if err != nil {
		l.logger.Print("Database exception: ", err)
		rw.WriteHeader(http.StatusBadRequest)
		return
	}
	rw.WriteHeader(http.StatusCreated)
}

func (l *LibraryHandler) MiddlewareAuthorDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		author := &data.Author{}
		err := author.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			l.logger.Fatal(err)
			return
		}
		ctx := context.WithValue(h.Context(), KeyProduct{}, author)
		h = h.WithContext(ctx)
		next.ServeHTTP(rw, h)
	})
}

func (l *LibraryHandler) MiddlewareBookDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		book := &data.Book{}
		err := book.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			l.logger.Fatal(err)
			return
		}
		ctx := context.WithValue(h.Context(), KeyProduct{}, book)
		h = h.WithContext(ctx)
		next.ServeHTTP(rw, h)
	})
}

func (l *LibraryHandler) MiddlewareMemberDeserialization(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		member := &data.Member{}
		err := member.FromJSON(h.Body)
		if err != nil {
			http.Error(rw, "Unable to decode json", http.StatusBadRequest)
			l.logger.Fatal(err)
			return
		}
		ctx := context.WithValue(h.Context(), KeyProduct{}, member)
		h = h.WithContext(ctx)
		next.ServeHTTP(rw, h)
	})
}

func (l *LibraryHandler) MiddlewareContentTypeSet(next http.Handler) http.Handler {
	return http.HandlerFunc(func(rw http.ResponseWriter, h *http.Request) {
		l.logger.Println("Method [", h.Method, "] - Hit path :", h.URL.Path)

		rw.Header().Add("Content-Type", "application/json")

		next.ServeHTTP(rw, h)
	})
}
