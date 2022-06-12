package main

import (
	"fmt"
	"log"
	"net/http"
	"net/http/pprof"
	"os"
)

/**
1. 接收客户端 request，并将 request 中带的 header 写入 response header
2. 读取当前系统的环境变量中的 VERSION 配置，并写入 response header
3. Server 端记录访问日志包括客户端 IP，HTTP 返回码，输出到 server 端的标准输出
4. 当访问 localhost/healthz 时，应返回 200
*/

func copyReqHead(w http.ResponseWriter, req *http.Request) {
	fmt.Println("Inside copyReqHead handler")
	for k := range req.Header {
		w.Header().Add(k, req.Header.Get(k))
	}

	fmt.Fprintf(w, "copyReqHead")
}

func readEnv(w http.ResponseWriter, req *http.Request) {
	fmt.Println("Inside readEnv handler")

	w.Header().Add("GOROOT", os.Getenv("GOROOT"))
	w.Header().Add("GOPATH", os.Getenv("GOPATH"))
	w.Header().Add("GOPROXY", os.Getenv("GOPROXY"))

	fmt.Fprintf(w, "readEnv")
}

func logAccessInfo(w http.ResponseWriter, req *http.Request) {
	fmt.Println("Inside logAccessInfo handler")

	addr := req.RemoteAddr
	w.WriteHeader(200)
	host := req.Host
	fmt.Println("addr:", addr, ", host:", host, ", http code = ", 200)
	fmt.Fprintf(w, addr)
}

func healthz(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(200)
	fmt.Fprintf(w, "is ok")
}

func main() {
	mux := http.NewServeMux()
	mux.HandleFunc("/copyReqHead", copyReqHead)
	mux.HandleFunc("/readEnv", readEnv)
	mux.HandleFunc("/logAccessInfo", logAccessInfo)
	mux.HandleFunc("/healthz", healthz)

	mux.HandleFunc("/debug/pprof/", pprof.Index)
	mux.HandleFunc("/debug/pprof/profile", pprof.Profile)
	mux.HandleFunc("/debug/pprof/symbol", pprof.Symbol)
	mux.HandleFunc("/debug/pprof/trace", pprof.Trace)

	err := http.ListenAndServe("localhost:8080", mux)
	if err != nil {
		log.Fatal("ListenAndServe: ", err.Error())
	}
}
