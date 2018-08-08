ROOT=$(shell pwd)
INCLUDE=-I$(ROOT)/../../lib/include
LIB=-L$(ROOT)/../../lib/lib
LIB_NAME=-lmysqlclient -lpthread
LD_FLAG=#-static

.PHONY:all
all:insertData

insertData:insertData.c
	gcc -o $@ $^ $(INCLUDE) $(LIB) $(LIB_NAME) $(LD_FLAG)

.PHONY:clean
clean:
	rm -f insertData
