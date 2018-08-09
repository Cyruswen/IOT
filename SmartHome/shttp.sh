#!/bin/bash
root=$(pwd)
hbin=bin/httpd

if [ -z $LD_LIBRARY_PATH ];then
    export LD_LIBRARY_PATH=$root/lib/lib
fi

./$hbin 8080
