#!/bin/bash

export METHOD=GET
#export QUERY_STRING="QUERY=app=ON1"
export QUERY_STRING="app=ADD"

#gdb ./tcp_client
./tcp_client
