package store

import (
	"errors"
	"example/query/domain"
)

type AccountStore struct {
	accounts map[string]*domain.Account
}

func NewAccountStore() domain.AccountStore {
	return AccountStore{make(map[string]*domain.Account)}
}

func (a AccountStore) Create(account domain.Account) error {
	_, ok := a.accounts[account.AccountNumber]
	if ok {
		return errors.New("account already exists")
	}
	a.accounts[account.AccountNumber] = &account
	return nil
}

func (a AccountStore) ReadAll() []domain.Account {
	accounts := make([]domain.Account, 0, len(a.accounts))
	for _, account := range a.accounts {
		accounts = append(accounts, *account)
	}
	return accounts
}

func (a AccountStore) Read(accountNumber string) (domain.Account, error) {
	account, ok := a.accounts[accountNumber]
	if !ok {
		return *account, errors.New("account not found")
	}
	return *account, nil
}

func (a AccountStore) Update(account domain.Account) error {
	_, ok := a.accounts[account.AccountNumber]
	if !ok {
		return errors.New("account not found")
	}
	a.accounts[account.AccountNumber] = &account
	return nil
}
