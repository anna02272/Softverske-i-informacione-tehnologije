package data

import (
	"context"
	"fmt"
	"log"
	"os"
	"time"

	// NoSQL: module containing Mongo api client
	influxdb2 "github.com/influxdata/influxdb-client-go/v2"
	"github.com/influxdata/influxdb-client-go/v2/api/write"
)

// NoSQL: TempRepo struct encapsulating InfluxDB API client
type TempRepo struct {
	cli influxdb2.Client
	// NoSQL: additional info about Organization and Bucket
	org string
	bucket string
	logger *log.Logger
}

// NoSQL: Constructor which reads db configuration from environment
func New(ctx context.Context, logger *log.Logger) (*TempRepo, error) {
	url := os.Getenv("INFLUX_DB_URL")
	token := os.Getenv("INFLUX_DB_TOKEN")
	
	org := os.Getenv("INFLUX_DB_ORG")
	bucket := os.Getenv("INFLUX_DB_BUCKET")

	client := influxdb2.NewClient(url, token)

	return &TempRepo{
		cli: client,
		org: org,
		bucket: bucket,
		logger: logger,
	}, nil
}

// Finish all async jobs and disconnect from database
func (tr *TempRepo) Disconnect(ctx context.Context) {
	tr.cli.Close()
}

// Check database connection
func (tr *TempRepo) CheckConnection() {
	ready, err := tr.cli.Ready(context.Background())
	if err != nil {
		tr.logger.Println(err)
	}
	tr.logger.Println(ready)
}

// Write Point to DB
func (tr *TempRepo) WriteGlobalTempPoint(tempPoint *GlobalTempPoint) error {
	// Initialise context (after 5 seconds timeout, abort operation)
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	writeAPI := tr.cli.WriteAPIBlocking(tr.org, tr.bucket)
	fieldsMap := tempPoint.ToFieldsMap()

	timestamp, err := time.Parse(time.RFC3339, tempPoint.Timestamp)
	if err != nil {
		tr.logger.Println(err)
		return err
	}

	point := write.NewPoint(globalMeasurement, nil, fieldsMap, timestamp)

	if err := writeAPI.WritePoint(ctx, point); err != nil {
		tr.logger.Println(err)
		return err
	}

	return nil
}

func (tr *TempRepo) WriteCityTempPoint(tempPoint *CityTempPoint) error {
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	writeAPI := tr.cli.WriteAPIBlocking(tr.org, tr.bucket)
	tagsMap := tempPoint.ToTagsMap()
	fieldsMap := tempPoint.ToFieldsMap()

	timestamp, err := time.Parse(time.RFC3339, tempPoint.Timestamp)
	if err != nil {
		tr.logger.Println(err)
		return err
	}

	point := write.NewPoint(cityMeasurement, tagsMap, fieldsMap, timestamp)

	if err := writeAPI.WritePoint(ctx, point); err != nil {
		tr.logger.Println(err)
		return err
	}

	return nil
}

func (tr *TempRepo) GetGlobalPointsInPastTime(timeRange string) (GlobalTempData, error) {
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	queryAPI := tr.cli.QueryAPI(tr.org)

	query := fmt.Sprintf(`from(bucket: "%s")
				|> range(start: -%s)
				|> filter(fn: (r) => r._measurement == "%s")`,
			tr.bucket, timeRange, globalMeasurement)
	
	// Define an empty map
	resultPoints := make(map[time.Time]GlobalTempPoint)
	results, err := queryAPI.Query(ctx, query)
	if err != nil {
		tr.logger.Println(err)
		return nil, err
	}
	for results.Next() {
		// Get Point from map
		val, ok := resultPoints[results.Record().Time()]

		// If not ok, Point with timestamp does not exist in map
		// => create it
		// If Point has tags, add tags (tags are present in all field TSM series)
		if !ok {
			val = GlobalTempPoint{}
		}
		// Read field from current TSM series
		// Switch with cases equal to every field defined in struct
		switch field := results.Record().Field(); field {
		case "LandAvgTemp":
			val.LandAvgTemp = results.Record().Value().(float64)
		case "LandMaxTemp":
			val.LandMaxTemp = results.Record().Value().(float64)
		case "LandMinTemp":
			val.LandMinTemp = results.Record().Value().(float64)
		default:
			tr.logger.Printf("Unrecognized field %s.\n", field)
		}

		// Add/Update Point in map
		resultPoints[results.Record().Time()] = val
	}
	if err := results.Err(); err != nil {
		tr.logger.Println(err)
		return nil, err
	}
	// Convert map to slice of struct data
	var data GlobalTempData
	for key, element := range resultPoints {
		element.Timestamp = key.String()
		data = append(data, element)
    }
	return data, nil
}

func (tr *TempRepo) GetCityPointsInPastTime(timeRange string) (CityTempData, error) {
	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()

	queryAPI := tr.cli.QueryAPI(tr.org)

	query := fmt.Sprintf(`from(bucket: "%s")
				|> range(start: -%s)
				|> filter(fn: (r) => r._measurement == "%s")`,
			tr.bucket, timeRange, cityMeasurement)
	
	// Define an empty map
	resultPoints := make(map[time.Time]CityTempPoint)
	results, err := queryAPI.Query(ctx, query)
	if err != nil {
		tr.logger.Println(err)
		return nil, err
	}
	for results.Next() {
		// Get Point from map
		val, ok := resultPoints[results.Record().Time()]

		// If not ok, Point with timestamp does not exist in map
		// => create it
		// If Point has tags, add tags (tags are present in all field TSM series)
		if !ok {
			val = CityTempPoint{
				City: results.Record().ValueByKey("City").(string),
				Country: results.Record().ValueByKey("Country").(string),
			}
		}
		// Read field from current TSM series
		// Switch with cases equal to every field defined in struct
		switch field := results.Record().Field(); field {
		case "AvgTemp":
			val.AvgTemp = results.Record().Value().(float64)
		default:
			tr.logger.Printf("Unrecognized field %s.\n", field)
		}

		// Add/Update Point in map
		resultPoints[results.Record().Time()] = val
	}
	if err := results.Err(); err != nil {
		tr.logger.Println(err)
		return nil, err
	}
	// Convert map to slice of struct data
	var data CityTempData
	for key, element := range resultPoints {
		element.Timestamp = key.String()
		data = append(data, element)
    }
	return data, nil
}
