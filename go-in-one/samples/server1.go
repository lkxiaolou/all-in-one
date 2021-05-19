package main

import (
	"fmt"
	"log"
	"net/http"
	_ "net/http/pprof"
	"time"
)

func main() {

	go func() {
		//example: visit http://127.0.0.1:6060/debug/pprof in browser.
		err := http.ListenAndServe("0.0.0.0:6060", nil)
		if err != nil {
			fmt.Println("failed to start pprof goroutine:", err)
		}
	}()

	http.HandleFunc("/", handler)
	log.Fatal(http.ListenAndServe("localhost:8000", nil))
}

func handler(w http.ResponseWriter, r *http.Request) {

	time.Sleep(1 * time.Second)

	eat()

	time := time.Now().Unix() * 2 + 1000000

	fmt.Fprintf(w, "URL.Path = %q; time = %d\n", r.URL.Path, time)
}

func eat() {
	loop := 10000000000
	for i := 0; i < loop; i++ {
		// do nothing
	}
}
