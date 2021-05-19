package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"strings"
	"time"
)

func main() {

	res := strings.Split("", ",")
	fmt.Printf("res = %+v\n", res)


	testMap := map[string]int{
		"1":1,
		"2":2,
		"3":3,
		"4":4,
		"5":5,
		"6":6,
		"7":7,
		"8":8,
		"9":9,
		"10":10,
	}

	rand.Seed(time.Now().UnixNano())

	go func() {
		for {
			testMap[strconv.Itoa(rand.Intn(10000))] = rand.Intn(10000)
		}
	}()

	for {
		fmt.Println("len = " + strconv.Itoa(len(testMap)))
	}

	//for k, _ := range testMap {
	//	fmt.Println("k = " + k)
	//}
}
