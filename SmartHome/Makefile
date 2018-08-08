ROOT=$(shell pwd)

.PHONY:all
all:httpd cgi
httpd:src/http.c
	gcc -I lib/include $^ -o bin/$@ -lpthread
cgi:
	cd $(ROOT)/webRoot/cgi;make clean;make;cd -

.PHONY:clean
clean:
	rm -rf bin/httpd core.*
	cd $(ROOT)/webRoot/cgi;make clean;cd -
