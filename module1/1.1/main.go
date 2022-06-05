package main

import (
	"fmt"
)

func main() {
	strArray := [5]string{"I", "am", "stupid", "and", "weak"}
	fmt.Println("before:", strArray)

	for i, s := range strArray {
		if s == "stupid" {
			strArray[i] = "smart"
		} else if s == "weak" {
			strArray[i] = "strong"
		}
	}

	fmt.Println("but now:", strArray)
}
