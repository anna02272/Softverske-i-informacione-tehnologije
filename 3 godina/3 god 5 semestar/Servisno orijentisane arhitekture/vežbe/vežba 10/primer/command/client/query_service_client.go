package client

import (
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"log"
	"net/http"
)

type GetAccountResponse struct {
	AccountNumber                      string
	HolderName                         string
	Balance                            uint32
	LastAppliedPayerPaymentEventNumber int64
}

type QueryServiceClient struct {
	address string
}

func NewQueryServiceClient(address string) QueryServiceClient {
	return QueryServiceClient{address: address}
}

func (c QueryServiceClient) GetAccount(accountNumber string) (GetAccountResponse, error) {
	url := fmt.Sprintf("http://%s/accounts/%s", c.address, accountNumber)
	httpReq, err := http.NewRequest(http.MethodGet, url, nil)
	if err != nil {
		log.Println(err)
		return GetAccountResponse{}, err
	}
	resp, err := http.DefaultClient.Do(httpReq)
	if err != nil {
		log.Println(err)
		return GetAccountResponse{}, err
	}
	if resp.StatusCode != http.StatusOK {
		log.Println(err)
		return GetAccountResponse{}, errors.New("status code not OK")
	}

	bodyBytes, err := io.ReadAll(resp.Body)
	if err != nil {
		log.Println(err)
		return GetAccountResponse{}, errors.New("could not read resp body")
	}

	var account GetAccountResponse
	err = json.Unmarshal(bodyBytes, &account)
	if err != nil {
		log.Println(err)
		return GetAccountResponse{}, err
	}

	return account, nil
}
