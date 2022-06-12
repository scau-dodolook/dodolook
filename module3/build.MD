## 构建 `docker build -t xdodolook/module3-httpserver:1.0 .`
``` bash
[root@cmp-workcluster module3]# docker build -t xdodolook/module3-httpserver:1.0 .
Sending build context to Docker daemon  4.608kB
Step 1/9 : FROM golang:alpine as builder
 ---> d8bf44a3f6b4
Step 2/9 : WORKDIR /go/src/github.com/httpserver
 ---> Using cache
 ---> ab63673a200e
Step 3/9 : COPY ./main.go .
 ---> Using cache
 ---> b172e46dad19
Step 4/9 : RUN go mod init
 ---> Using cache
 ---> d294d4d4ae78
Step 5/9 : RUN CGO_ENABLED=0 GOOS=linux go build -o main
 ---> Using cache
 ---> cdd4e66a99dc
Step 6/9 : FROM alpine:latest as prod
 ---> c059bfaa849c
Step 7/9 : WORKDIR /root/
 ---> Using cache
 ---> 7413d85caecf
Step 8/9 : COPY --from=builder /go/src/github.com/httpserver/main .
 ---> Using cache
 ---> e4d7c4e25c27
Step 9/9 : ENTRYPOINT ["./main"]
 ---> Using cache
 ---> fd65b765e538
Successfully built fd65b765e538
Successfully tagged xdodolook/module3-httpserver:1.0
```

## 推送镜像 `docker push xdodolook/module3-httpserver:1.0`
``` bash
[root@cmp-workcluster module3]# docker build -t xdodolook/module3-httpserver:1.0 .
Sending build context to Docker daemon  4.608kB
Step 1/9 : FROM golang:alpine as builder
 ---> d8bf44a3f6b4
Step 2/9 : WORKDIR /go/src/github.com/httpserver
 ---> Using cache
 ---> ab63673a200e
Step 3/9 : COPY ./main.go .
 ---> Using cache
 ---> b172e46dad19
Step 4/9 : RUN go mod init
 ---> Using cache
 ---> d294d4d4ae78
Step 5/9 : RUN CGO_ENABLED=0 GOOS=linux go build -o main
 ---> Using cache
 ---> cdd4e66a99dc
Step 6/9 : FROM alpine:latest as prod
 ---> c059bfaa849c
Step 7/9 : WORKDIR /root/
 ---> Using cache
 ---> 7413d85caecf
Step 8/9 : COPY --from=builder /go/src/github.com/httpserver/main .
 ---> Using cache
 ---> e4d7c4e25c27
Step 9/9 : ENTRYPOINT ["./main"]
 ---> Using cache
 ---> fd65b765e538
Successfully built fd65b765e538
Successfully tagged xdodolook/module3-httpserver:1.0
[root@cmp-workcluster module2]# docker push xdodolook/module3-httpserver:1.0
The push refers to repository [docker.io/xdodolook/module3-httpserver]
cdb0f1fdd95a: Pushed
8d3ac3489996: Layer already exists
1.0: digest: sha256:b339b3c66dc5bfa195c6cbaaf165df11f46b1a395ee5ae200b39cb57816546f8 size: 739
``` 

## 运行 `docker run -d -p8080:8080 xdodolook/module3-httpserver:1.0`
``` bash
[root@cmp-workcluster module2]# docker run -d -p8080:8080 xdodolook/module3-httpserver:1.0
84eedf3925e523e4f35ec5e93c517a32cccd188d29e08f4bf5ee69b773ad18d5
```

## 查看容器网络配置 `nsenter -t 25042 -n ip a`
```bash
[root@cmp-workcluster module2]# docker inspect 84eedf3925e523e4f35ec5e93c517a32cccd188d29e08f4bf5ee69b773ad18d5 | grep Pid
"Pid": 25042,
"PidMode": "",
"PidsLimit": null,
[root@cmp-workcluster module2]# nsenter -t 25042 -n ip a
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
inet 127.0.0.1/8 scope host lo
valid_lft forever preferred_lft forever
56: eth0@if57: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
link/ether 02:42:ac:11:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
inet 172.17.0.2/16 brd 172.17.255.255 scope global eth0
valid_lft forever preferred_lft forever
```
