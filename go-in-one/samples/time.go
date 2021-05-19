package main

import (
	"fmt"
	"time"
)

func main()  {
	//start := time.Now()
	//time.Sleep(time.Second)
	//gap := time.Now().Sub(start).Milliseconds()
	//fmt.Printf("gap = %v", int(gap))

	ticker := time.NewTicker(time.Duration(2 * time.Second))
	defer ticker.Stop()
	for {
		select {
		case <-ticker.C:
			fmt.Println(time.Now().String())
			time.Sleep(5 * time.Second)
		}
	}
}
