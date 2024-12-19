package data

import (
	"errors"
	"log"
	"os"
	"time"

	"github.com/google/uuid"

	// NoSQL: module containing PostgreSQL driver
	"gorm.io/driver/postgres"
	// NoSQL: module containing GORM - Object-Relation mapper for Golang
	"gorm.io/gorm"
)

// NoSQL: LibraryRepo struct encapsulating Cockroach DB session
type LibraryRepo struct {
	db     *gorm.DB
	logger *log.Logger
}

// NoSQL: Constructor which reads db configuration from environment and creates a keyspace
func New(logger *log.Logger) (*LibraryRepo, error) {
	dbUri := os.Getenv("COCKROACH_DB")

	logger.Print(dbUri)

	db, err := gorm.Open(postgres.Open(dbUri), &gorm.Config{})
	if err != nil {
		log.Fatal(err)
	}

	// Automatically create tables based on Go struct
	db.AutoMigrate(&Book{})
	db.AutoMigrate(&Author{})
	db.AutoMigrate(&Member{})
	db.AutoMigrate(&Borrowing{})

	// Return repository with logger and DB session
	return &LibraryRepo{
		db:     db,
		logger: logger,
	}, nil
}

func (lr *LibraryRepo) InsertAuthor(author *Author) error {
	err := lr.db.Create(author).Error
	if err != nil {
		lr.logger.Println(err)
		return err
	}
	lr.logger.Println("Author inserted")
	return nil
}

func (lr *LibraryRepo) GetAuthors() (Authors, error) {
	lr.logger.Println("Getting all Authors")
	var authors Authors
	err := lr.db.Find(&authors).Error
	if err != nil {
		return nil, err
	}
	return authors, nil
}

func (lr *LibraryRepo) InsertBook(book *Book) error {
	err := lr.db.Create(book).Error
	if err != nil {
		lr.logger.Println(err)
		return err
	}
	lr.logger.Println("Book inserted")
	return nil
}

func (lr *LibraryRepo) GetBooks() (Books, error) {
	lr.logger.Println("Getting all Books")
	var books Books
	err := lr.db.Find(&books).Error
	if err != nil {
		return nil, err
	}
	return books, nil
}

func (lr *LibraryRepo) GetBookByIsbn(isbn string) (*Book, error) {
	var book Book
	err := lr.db.Preload("Author").First(&book, isbn).Error
	if err != nil {
		return nil, err
	}
	return &book, nil
}

func (lr *LibraryRepo) InsertMember(member *Member) error {
	err := lr.db.Create(member).Error
	if err != nil {
		lr.logger.Println(err)
		return err
	}
	lr.logger.Println("Member inserted")
	return nil
}

func (lr *LibraryRepo) GetMembers() (Members, error) {
	lr.logger.Println("Getting all Members")
	var members Members
	err := lr.db.Find(&members).Error
	if err != nil {
		return nil, err
	}
	return members, nil
}

// NoSQL: Transactional control with optimistic concurency control (OCC)
// CockroachDB relies on timestamps and data versioning to implement optimistic locking
// Example flow:
// 1) Tx A reads tha data Book data with specific ISBN from the DB
// 2) Tx B reads the data Book data with the same ISBN  from the DB
// 3) Tx A makes local changes to the data
// 4) Tx A commits the changes to the DB
// 5) Tx B makes local changes to the data
// 6) Tx B tries to commit changes
// In moment 6) the Tx B should get an optimistic concurency control error
// because there was a conflict while commiting the data
// We support this by optimistically locking the row inside the Book table with the specified ISBN
func (lr *LibraryRepo) BorrowBook(isbn string, memberId string) error {
	// Begin a new transaction
	tx := lr.db.Begin()

	// Defer a rollback in case of an error
	defer func() {
		if r := recover(); r != nil {
			tx.Rollback()
		}
	}()

	// Check if the book is available
	var book Book
	if err := tx.Where("isbn = ?", isbn).First(&book).Error; err != nil {
		tx.Rollback()
		return err
	}
	if !book.IsAvailable {
		tx.Rollback()
		return errors.New("Book is not available for borrowing")
	}

	// Update the book availability
	book.IsAvailable = false
	if err := tx.Save(&book).Error; err != nil {
		tx.Rollback()
		return err
	}

	memberIdUuid, _ := uuid.Parse(memberId)
	// Create a new borrowing record
	borrowing := Borrowing{
		MemberId:     memberIdUuid,
		BookIsbn:     isbn,
		BorrowedTime: time.Now(),
	}
	if err := tx.Create(&borrowing).Error; err != nil {
		tx.Rollback()
		return err
	}

	// Commit the transaction
	tx.Commit()
	return nil
}
