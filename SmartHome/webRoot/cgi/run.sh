#!/bin/bash

export METHOD=GET
#export QUERY_STRING="QUERY=app=ON1"
export QUERY_STRING="app=ADD"

#gdb ./tcp_client
strace -f -tt -T -o tcpclientout ./tcp_client
