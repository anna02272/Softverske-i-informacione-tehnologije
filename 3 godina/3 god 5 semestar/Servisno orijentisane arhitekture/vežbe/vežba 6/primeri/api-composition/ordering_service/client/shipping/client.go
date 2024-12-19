package shipping

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
	"log"
	"net/http"
)

type Client struct {
	address string
}

func NewClient(host, port string) Client {
	return Client{
		address: fmt.Sprintf("http://%s:%s", host, port),
	}
}

func (client Client) Get(id string) (*GetOrderShippingInfoResponse, error) {
	requestURL := client.address + "/" + id
	httpReq, err := http.NewRequest(http.MethodGet, requestURL, nil)

	if err != nil {
		log.Println(err)
		return nil, errors.New("error while getting shipping info")
	}

	res, err := http.DefaultClient.Do(httpReq)

	if err != nil || res.StatusCode != http.StatusOK {
		log.Println(err)
		log.Println(res.StatusCode)
		return nil, errors.New("error while getting shipping info")
	}

	order := &GetOrderShippingInfoResponse{}
	json.NewDecoder(res.Body).Decode(order)

	return order, nil
}

func (client Client) ScheduleOrderShipping(orderId, shippingAddress string) error {
	req := ScheduleOrderRequest{
		Id:              orderId,
		ShippingAddress: shippingAddress,
	}

	reqBytes, err := json.Marshal(req)
	if err != nil {
		log.Println(err)
		return err
	}

	bodyReader := bytes.NewReader(reqBytes)
	requestURL := client.address + "/"
	httpReq, err := http.NewRequest(http.MethodPost, requestURL, bodyReader)

	if err != nil {
		log.Println(err)
		return errors.New("error cancelling shipping")
	}

	res, err := http.DefaultClient.Do(httpReq)

	if err != nil || res.StatusCode != http.StatusOK {
		log.Println(err)
		log.Println(res.StatusCode)
		return errors.New("error cancelling shipping")
	}
	return nil
}

func (client Client) CancelOrderShipping(orderId, shippingAddress string) error {
	req := ScheduleOrderRequest{
		Id:              orderId,
		ShippingAddress: shippingAddress,
	}

	reqBytes, err := json.Marshal(req)
	if err != nil {
		return err
	}

	bodyReader := bytes.NewReader(reqBytes)
	requestURL := client.address + "/"
	httpReq, err := http.NewRequest(http.MethodDelete, requestURL, bodyReader)

	if err != nil {
		log.Println(err)
		return errors.New("error cancelling shipping")
	}

	res, err := http.DefaultClient.Do(httpReq)

	if err != nil || res.StatusCode != http.StatusOK {
		log.Println(err)
		log.Println(res.StatusCode)
		return errors.New("error cancelling shipping")
	}
	return nil
}
