module example/follow

go 1.19

require (
	example/common v1.0.0
	github.com/gorilla/mux v1.8.0
)

require (
	github.com/klauspost/compress v1.15.12 // indirect
	github.com/minio/highwayhash v1.0.2 // indirect
	github.com/nats-io/jwt/v2 v2.3.0 // indirect
	github.com/nats-io/nats.go v1.20.0 // indirect
	github.com/nats-io/nkeys v0.3.0 // indirect
	github.com/nats-io/nuid v1.0.1 // indirect
	golang.org/x/crypto v0.0.0-20220926161630-eccd6366d1be // indirect
	golang.org/x/time v0.2.0 // indirect
)

replace example/common => ../common
