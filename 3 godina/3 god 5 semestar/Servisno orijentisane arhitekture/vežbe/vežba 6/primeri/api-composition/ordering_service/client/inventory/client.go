package inventory

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
	"github.com/tamararankovic/microservices_demo/ordering_service/domain"
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

func (client Client) UpdateInventory(order *domain.Order) error {
	products := make([]Product, 0)
	for _, item := range order.Items {
		product := Product{
			ProductId: item.Product.Id,
			ColorCode: item.Product.Color.Code,
			Quantity:  -int64(item.Quantity),
		}
		products = append(products, product)
	}

	reqBytes, err := json.Marshal(UpdateInventoryRequest{products})
	if err != nil {
		return err
	}

	bodyReader := bytes.NewReader(reqBytes)
	requestURL := client.address + "/"
	httpReq, err := http.NewRequest(http.MethodPost, requestURL, bodyReader)

	if err != nil {
		log.Println(err)
		return errors.New("error updating inventory")
	}

	res, err := http.DefaultClient.Do(httpReq)

	if err != nil || res.StatusCode != http.StatusOK {
		log.Println(err)
		log.Println(res.StatusCode)
		return errors.New("error updating inventory")
	}
	return nil
}
