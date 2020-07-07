package main

import (
	"fmt"
	"os"
)

func main() {
	fmt.Println(os.Args[0])
	for index, value := range os.Args {
		fmt.Println(index)
		fmt.Println(value)
	}
}
