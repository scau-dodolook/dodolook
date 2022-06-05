package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
)

func copyReqHead(w http.ResponseWriter, req *http.Request) {
	fmt.Println("Inside copyReqHead handler")
	for k := range req.Header {
		w.Header().Add(k, req.Header.Get(k))
	}

	fmt.Fprintf(w, "Hello,"+req.URL.Path[1:])
}

func readEnv(w http.ResponseWriter, req *http.Request) {
	fmt.Println("Inside readEnv handler")

	w.Header().Add("GOROOT", os.Getenv("GOROOT"))
	w.Header().Add("GOPATH", os.Getenv("GOPATH"))
	w.Header().Add("GOPROXY", os.Getenv("GOPROXY"))

	fmt.Fprintf(w, "read env")
}

func logAccessInfo(w http.ResponseWriter, req *http.Request) {
	fmt.Println("Inside logAccessInfo handler")

	addr := req.RemoteAddr
	w.WriteHeader(200)
	host := req.Host
	fmt.Println("addr:", addr, ", host:", host, ", http code = ", w.Header().Get())
}

func main() {
	http.HandleFunc("/copyReqHead", copyReqHead)
	http.HandleFunc("/readEnv", readEnv)
	http.HandleFunc("/logAccessInfo", logAccessInfo)

	err := http.ListenAndServe("localhost:8080", nil)
	if err != nil {
		log.Fatal("ListenAndServe: ", err.Error())
	}
}
