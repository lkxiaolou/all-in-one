package main

import (
	"fmt"
	"runtime"
)

func main() {
	callA()
}

func callA()  {
	callB()
}

func callB()  {
	pc, _, _, _ := runtime.Caller(1)
	fmt.Printf("caller %v", runtime.FuncForPC(pc).Name())
}
