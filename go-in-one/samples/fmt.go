package main

import (
	"errors"
	"fmt"
)

type Data struct {
	success bool
	message string
}

func main() {
	// 本例展示 %v、%+v、%T、#v的区别
	// 包括 字符串、结构体、map、切片、error
	actions := []string{
		"%v","%+v","%T","%#v",
	}

	vars := make([]interface{}, 0)
	vars = append(vars, "12345")
	vars = append(vars, 10086)
	vars = append(vars, true)
	vars = append(vars, []int{1, 2, 3})
	vars = append(vars, Data{success: true, message: "hello"})
	vars = append(vars, map[string]string{"hello":"world"})
	vars = append(vars, []string{"hello","world"})
	vars = append(vars, errors.New("this is error"))

	for _, action := range actions {
		fmt.Println("===" + action + "===")
		for _, val := range vars {
			fmt.Println(fmt.Sprintf(action, val))
		}
	}

	/**
	===%v===
	12345
	10086
	true
	[1 2 3]
	{true hello}
	map[hello:world]
	[hello world]
	this is error
	===%+v===
	12345
	10086
	true
	[1 2 3]
	{success:true message:hello}
	map[hello:world]
	[hello world]
	this is error
	===%T===
	string
	int
	bool
	[]int
	main.Data
	map[string]string
	[]string
	*errors.errorString
	===%#v===
	"12345"
	10086
	true
	[]int{1, 2, 3}
	main.Data{success:true, message:"hello"}
	map[string]string{"hello":"world"}
	[]string{"hello", "world"}
	&errors.errorString{s:"this is error"}
	*/

}
