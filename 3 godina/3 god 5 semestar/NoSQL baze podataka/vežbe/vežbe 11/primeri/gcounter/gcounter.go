//inspired by: https://github.com/neurodrone/crdt/blob/master/g_counter.go
package main

import "fmt"

// GCounter represent a G-counter in CRDT, which is
// a state-based grow-only counter that only supports
// increments.
type GCounter struct {
	id string

	// counter maps identity of each replica to their
	// entry values i.e. the counter value they individually
	// have.
	counter map[string]int
}

// NewGCounter returns a *GCounter
func NewGCounter(id string) *GCounter {
	return &GCounter{
		id:   id,
		counter: make(map[string]int),
	}
}

// Inc increments the GCounter by the value of 1 everytime it
// is called.
func (g *GCounter) Increment() {
	g.counter[g.id] += 1
}


// Count returns the total count of this counter across all the
// present replicas.
func (g *GCounter) Count() (total int) {
	for _, val := range g.counter {
		total += val
	}
	return
}

// Merge combines the counter values across multiple replicas.
// The property of idempotency is preserved here across
// multiple merges as when no state is changed across any replicas,
// the result should be exactly the same everytime.
func (g *GCounter) Merge(c *GCounter) {
	for id, val := range c.counter {
		if v, ok := g.counter[id]; !ok || v < val {
			g.counter[id] = val
		}
	}
}

func (g* GCounter) PrintCurrentState(){
	fmt.Printf("--------%s-------- \n", g.id)
	for id, val := range g.counter {
		fmt.Printf("id: %s, val: %d \n", id, val)
	}
	fmt.Printf("---------------------\n\n")
}