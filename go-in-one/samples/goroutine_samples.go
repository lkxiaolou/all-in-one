package main

import (
	"fmt"
	"sync"
)

func main()  {
	go count()

	go wait()

	wait()
}

func count()  {
	count := 0
	for {
		count = count + 1
		if count % 1000000000 == 0 {
			fmt.Println("I'm a running routine")
		}
	}
}

func wait()  {
	wg := sync.WaitGroup{}
	wg.Add(1)
	wg.Wait()
}
