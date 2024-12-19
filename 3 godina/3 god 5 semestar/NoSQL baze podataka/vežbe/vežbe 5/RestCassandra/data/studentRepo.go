package data

import (
	"fmt"
	"log"
	"os"

	// NoSQL: module containing Cassandra api client
	"github.com/gocql/gocql"
)

// NoSQL: StudentRepo struct encapsulating Cassandra api client
type StudentRepo struct {
	session *gocql.Session
	logger *log.Logger
}

// NoSQL: Constructor which reads db configuration from environment and creates a keyspace
func New(logger *log.Logger) (*StudentRepo, error) {
	db := os.Getenv("CASS_DB")

	// Connect to default keyspace
	cluster := gocql.NewCluster(db)
	cluster.Keyspace = "system"
	session, err := cluster.CreateSession()
	if err != nil {
		logger.Println(err)
		return nil, err
	}
	// Create 'student' keyspace
	err = session.Query(
		fmt.Sprintf(`CREATE KEYSPACE IF NOT EXISTS %s
					WITH replication = {
						'class' : 'SimpleStrategy',
						'replication_factor' : %d
					}`, "student", 1)).Exec()
    if err != nil {
        logger.Println(err)
    }
	session.Close()

	// Connect to student keyspace
	cluster.Keyspace = "student"
	cluster.Consistency = gocql.One
	session, err = cluster.CreateSession()
	if err != nil {
		logger.Println(err)
		return nil, err
	}
	
	// Return repository with logger and DB session
	return &StudentRepo{
		session: session,
		logger: logger,
	}, nil
}

// Disconnect from database
func (sr *StudentRepo) CloseSession() {
	sr.session.Close()
}

// Create ocene_by_student and ocene_by_predmet tables
func (sr *StudentRepo) CreateTables() {
	err := sr.session.Query(
		fmt.Sprintf(`CREATE TABLE IF NOT EXISTS %s 
					(student_id UUID, ime text, prezime text, indeks text, ocena int, datum date, id UUID, predmet text, 
					PRIMARY KEY ((student_id), datum, ocena)) 
					WITH CLUSTERING ORDER BY (datum ASC, ocena DESC)`, 
					"ocene_by_student")).Exec()
	if err != nil {
		sr.logger.Println(err)
	}
	err = sr.session.Query(
		fmt.Sprintf(`CREATE TABLE IF NOT EXISTS %s 
					(predmet_id UUID, indeks text, ocena int, datum_id TIMEUUID, naziv text, ime text, prezime text, 
					PRIMARY KEY ((predmet_id), indeks, datum_id)) 
					WITH CLUSTERING ORDER BY (indeks ASC, datum_id DESC)`, 
					"ocene_by_predmet")).Exec()
	if err != nil {
		sr.logger.Println(err)
	}
}

func (sr *StudentRepo) GetOceneByStudent(id string) (OceneByStudent, error) {
	scanner := sr.session.Query(`SELECT student_id, ime, prezime, indeks, ocena, id, datum, predmet FROM ocene_by_student WHERE student_id = ?`,
					id).Iter().Scanner()
	
	var ocene OceneByStudent
	for scanner.Next() {
		var stud OcenaByStudent
		err := scanner.Scan(&stud.StudentId, &stud.Ime, &stud.Prezime, &stud.Indeks, &stud.Ocena, &stud.Id, &stud.Datum, &stud.Predmet)
		if err != nil {
			sr.logger.Println(err)
			return nil, err
		}
		ocene = append(ocene, &stud)
	}
	if err := scanner.Err(); err != nil {
		sr.logger.Println(err)
		return nil, err
	}
	return ocene, nil
}

func (sr *StudentRepo) GetOceneByPredmet(id string) (OceneByPredmet, error) {
	scanner := sr.session.Query(`SELECT predmet_id, naziv, indeks, ime, prezime, ocena, toTimestamp(datum_id) FROM ocene_by_predmet WHERE predmet_id = ?`,
					id).Iter().Scanner()
	
	var ocene OceneByPredmet
	for scanner.Next() {
		var pred OcenaByPredmet
		err := scanner.Scan(&pred.PredmetId, &pred.Naziv, &pred.Indeks, &pred.Ime, &pred.Prezime, &pred.Ocena, &pred.DatumId)
		if err != nil {
			sr.logger.Println(err)
			return nil, err
		}
		ocene = append(ocene, &pred)
	}
	if err := scanner.Err(); err != nil {
		sr.logger.Println(err)
		return nil, err
	}
	return ocene, nil
}

func (sr *StudentRepo) InsertOcenaByStudent(studentOcena *OcenaByStudent) (error) {
	ocenaId, _ := gocql.RandomUUID()
	err := sr.session.Query(
		`INSERT INTO ocene_by_student (student_id, ime, prezime, indeks, ocena, datum, id, predmet) 
		VALUES (?, ?, ?, ?, ?, ?, ?, ?)`,
		studentOcena.StudentId, studentOcena.Ime, studentOcena.Prezime, studentOcena.Indeks,
		studentOcena.Ocena, studentOcena.Datum, ocenaId, studentOcena.Predmet).Exec()
	if err != nil {
		sr.logger.Println(err)
		return err
	}
	return nil
}

func (sr *StudentRepo) InsertOcenaByPredmet(predmetOcena *OcenaByPredmet) (error) {
	datumId := gocql.TimeUUID()
	err := sr.session.Query(
		`INSERT INTO ocene_by_predmet (predmet_id, indeks, ocena, datum_id, naziv, ime, prezime) 
		VALUES (?, ?, ?, ?, ?, ?, ?)`,
		predmetOcena.PredmetId, predmetOcena.Indeks, predmetOcena.Ocena,
		datumId, predmetOcena.Naziv, predmetOcena.Ime, predmetOcena.Prezime).Exec()
	if err != nil {
		sr.logger.Println(err)
		return err
	}
	return nil
}

// NoSQL: Performance issue, we never want to fetch all the data
// (In order to get all student ids we need to contact every partition which are usually located on different servers!)
// Here we are doing it for demonstration purposes (so we can see all student/predmet ids)
func (sr *StudentRepo) GetDistinctIds(idColumnName string, tableName string) ([]string, error) {
	scanner := sr.session.Query(
		fmt.Sprintf(`SELECT DISTINCT %s FROM %s`, idColumnName, tableName)).
		Iter().Scanner()
	var ids []string
	for scanner.Next() {
		var id string
		err := scanner.Scan(&id)
		if err != nil {
			sr.logger.Println(err)
			return nil, err
		}
		ids = append(ids, id)
	}
	if err := scanner.Err(); err != nil {
		sr.logger.Println(err)
		return nil, err
	}
	return ids, nil
}
