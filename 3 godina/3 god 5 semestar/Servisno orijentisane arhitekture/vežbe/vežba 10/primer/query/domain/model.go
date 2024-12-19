package domain

type Account struct {
	AccountNumber                      string
	HolderName                         string
	Balance                            uint32
	LastAppliedPayerPaymentEventNumber int64
}
