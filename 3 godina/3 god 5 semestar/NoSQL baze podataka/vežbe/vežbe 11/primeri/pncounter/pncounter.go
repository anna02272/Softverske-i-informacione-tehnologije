//inspired by: https://github.com/neurodrone/crdt/blob/master/pn_counter.go
package main

// PNCounter represents a state-based postive-negative counter. It is
// implemented as sets of two G-Counters, one that tracks
// increments while the other decrements.
type PNCounter struct {
	pCounter *GCounter
	nCounter *GCounter
}

// NewPNCounter returns a new *PNCounter with both its
// G-Counters initialized.
func NewPNCounter(id string) *PNCounter {
	return &PNCounter{
		pCounter: NewGCounter(id),
		nCounter: NewGCounter(id),
	}
}

// Inc monotonically increments the current value of the
// PN-Counter by one.
func (pn *PNCounter) Increment() {
	pn.pCounter.Increment()
}


// Dec monotonically decrements the current value of the
// PN-Counter by one.
func (pn *PNCounter) Decrement() {
	pn.nCounter.Increment()
}


// Count returns the current value of the counter. It
// subtracts the value of negative G-Counter from the
// positive grow-only counter and returns the result.
// Because this counter can grow in either direction,
// negative integers as results are possible.
func (pn *PNCounter) Count() int {
	return pn.pCounter.Count() - pn.nCounter.Count()
}

// Merge combines both the PN-Counters and saves the result
// in the invoking counter. 
func (pn *PNCounter) Merge(pnpn *PNCounter) {
	pn.pCounter.Merge(pnpn.pCounter)
	pn.nCounter.Merge(pnpn.nCounter)
}

func (pn *PNCounter) PrintCurrentState(){
	pn.pCounter.PrintCurrentState("positive")
	pn.nCounter.PrintCurrentState("negative")
}