#!/bin/bash
root=$(pwd)
bin=bin/httpd

if [ -z $LD_LIBRARY_PATH ];then
    export LD_LIBRARY_PATH=$root/lib/lib
fi

./$bin 8080
