package main

import (
	"encoding/json"
	"fmt"
)

func main()  {
	tm := map[string]string{"hello":"world"}
	rb,_ := json.Marshal(tm)

	fmt.Println(string(rb))
}
