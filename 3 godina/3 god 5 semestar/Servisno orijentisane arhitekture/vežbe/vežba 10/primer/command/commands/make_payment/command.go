package make_payment

import (
	"errors"
	"example/command/commands"
)

var (
	ErrInsufficientFunds = errors.New("insufficient funds")
	ErrBalanceChanged    = errors.New("balance changed")
)

type AccountInfo struct {
	Balance                            uint32
	LastAppliedPayerPaymentEventNumber int64
}

type MakePaymentCommand struct {
	PayerAccountNumber string
	PayeeAccountNumber string
	Amount             uint32
	PayerAccount       AccountInfo
}

func NewCommand(payerAccountNumber, payeeAccountNumber string, amount uint32, payerAccount AccountInfo) commands.Command {
	return &MakePaymentCommand{
		PayerAccountNumber: payerAccountNumber,
		PayeeAccountNumber: payeeAccountNumber,
		Amount:             amount,
		PayerAccount:       payerAccount,
	}
}
