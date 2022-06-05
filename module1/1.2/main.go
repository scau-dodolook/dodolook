package main

import (
	"fmt"
	"time"
)

var ic1 = make(chan int, 10)
var ic2 = make(chan int, 10)
var dc = make(chan bool, 1)

func main() {
	for i := 0; i < 100; i++ {
		if i%2 == 1 {
			go func() {
				ic1 <- i
				fmt.Println("1 ", i)
				time.Sleep(time.Second)

			}()
		} else if i%2 == 0 {
			go func() {
				ic2 <- i
				fmt.Println("0 ", i)
				time.Sleep(2 * time.Second)
			}()
		}
	}

	for true {
		select {
		case v := <-ic1:
			fmt.Println(v)
		case v := <-ic2:
			fmt.Println(v)
		case <-dc:
			return
		default:
			fmt.Println("nothing")
		}

		time.Sleep(time.Second)
	}

}
