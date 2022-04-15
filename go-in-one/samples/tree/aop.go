package main

import "fmt"

func main() {
	fmt.Println(execute("roshi"))
}

func execute(name string) string {
	return name
}

// before:fmt.Println("before...")
func executeComment(name string) string {
	return name
}
