package create_account

import (
	"example/command/commands"
)

type CreateAccountCommand struct {
	HolderName string
}

func NewCommand(holderName string) commands.Command {
	return &CreateAccountCommand{HolderName: holderName}
}
