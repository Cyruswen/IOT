ROOT=$(shell pwd)
INCLUDE=-I$(ROOT)/../../lib/include
LIB=-L$(ROOT)/../../lib/lib
LIB_NAME=-lmysqlclient -lpthread
LD_FLAG=#-static

.PHONY:all
all:tcp_client

tcp_client:tcp_client.cpp
	g++ -g -o $@ $^ $(INCLUDE) $(LIB) $(LIB_NAME) $(LD_FLAG)

.PHONY:clean
clean:
	rm -rf tcp_client
