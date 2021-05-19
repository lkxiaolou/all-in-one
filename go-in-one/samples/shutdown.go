package main

import (
	"fmt"
	"os"
	"os/signal"
	"syscall"
)

func main() {
	go signalListen()
	fmt.Println("I'm running...")
}

func signalListen()  {
	signalChan := make(chan os.Signal, 1)
	signal.Notify(signalChan, syscall.SIGINT, syscall.SIGTERM)
	s := <-signalChan
	fmt.Println("receive signal " + s.String())
}