package main

import (
	"fmt"
	"os"
)

func main() {
	// 初始空字符串
	var s, sep string

	// :=短变量声明
	for i := 1; i < len(os.Args); i++ {
		s += sep + os.Args[i]
		sep = " "
	}
	fmt.Println(s)
}
