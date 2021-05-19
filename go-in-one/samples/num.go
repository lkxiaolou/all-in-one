package main

import "fmt"

func main() {
	maxValue := 235
	minValue := 153
	MinCleanRate := 0.5
	if (float64((maxValue - minValue) / maxValue) < MinCleanRate) {
		fmt.Printf("yes")
	} else {
		fmt.Printf("no")
	}
}