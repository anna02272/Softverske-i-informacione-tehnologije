package main

import "fmt";
	
func main(){

	node1 := NewGCounter("node1");
	node2 := NewGCounter("node2");
	node3 := NewGCounter("node3");


	//node1 - 3 new likes
	for i := 0; i<3; i++{
		node1.Increment()
	}

	//No other replicas registered
	node1.PrintCurrentState()

	//count = 3, that is true number of likes
	fmt.Printf("Node1 count: %d\n\n", node1.Count())

	//node2 - 5 new likes
	for i := 0; i<5; i++{
		node2.Increment()
	}

	//count = 5, true number of likes is 8
	fmt.Printf("Node2 count: %d\n\n", node2.Count())
	
	//node 3 - one new like
	node3.Increment()

	node1.Merge(node2)
	node1.Merge(node3)

	node1.PrintCurrentState()

	//idempotence
	node2.Merge(node3)
	node2.PrintCurrentState()
	node2.Merge(node3)
	node2.PrintCurrentState()

	node2.Increment()

	//Commutativity
	node2.Merge(node1)
	node1.Merge(node2)

	node1.PrintCurrentState()
	node2.PrintCurrentState()

}