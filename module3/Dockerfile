FROM golang:alpine as builder

WORKDIR /go/src/github.com/httpserver

COPY ./main.go .

RUN go mod init
RUN CGO_ENABLED=0 GOOS=linux go build -o main

FROM alpine:latest as prod

WORKDIR /root/

COPY --from=builder /go/src/github.com/httpserver/main .

ENTRYPOINT ["./main"]