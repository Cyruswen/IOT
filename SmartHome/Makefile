ROOT=$(shell pwd)

.PHONY:all
all:httpd cgi tcpd

httpd:src/http.c
	gcc -g -I lib/include $^ -o bin/$@ -lpthread
tcpd:src/tcp.cpp
	g++ -g -I lib/include $^ -o bin/$@ -lpthread -std=c++11
cgi:
	cd $(ROOT)/webRoot/cgi;make clean;make;cd -

.PHONY:clean
clean:
	rm -rf bin/httpd core.*
	rm -rf bin/tcpd
	cd $(ROOT)/webRoot/cgi;make clean;cd -
