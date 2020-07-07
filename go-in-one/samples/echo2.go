package main

import (
	"fmt"
	"os"
)

func main() {
	// 短变量申明，只能在函数内部
	s, sep := "", ""
	// rang 产生索引和值，_空标志符
	for _, arg := range os.Args[1:] {
		s += sep + arg
		sep = " "
	}
	fmt.Println(s)
}
