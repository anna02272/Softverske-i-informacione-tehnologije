package domain

type AccountStore interface {
	Create(account Account) error
	ReadAll() []Account
	Read(accountNumber string) (Account, error)
	Update(account Account) error
}
