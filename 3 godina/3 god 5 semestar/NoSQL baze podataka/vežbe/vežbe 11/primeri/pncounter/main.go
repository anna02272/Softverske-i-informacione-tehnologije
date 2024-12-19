package main

import "fmt";
	
func main(){
	node1 := NewPNCounter("node1")
	node2 := NewPNCounter("node2")

	for i := 0; i<3; i++{
		node1.Increment()
	}

	for i := 0; i<2; i++{
		node1.Decrement()
	}

	node2.Increment()
	node2.Decrement()
	node1.Merge(node2)
	node2.Merge(node1)

	node1.PrintCurrentState()

	fmt.Printf("Node1 count: %d \n\n", node1.Count())
	fmt.Printf("Node2 count: %d \n\n", node2.Count())


}
