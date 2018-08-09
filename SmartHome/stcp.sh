#!/bin/bash
root=$(pwd)
tbin=bin/tcpd

if [ -z $LD_LIBRARY_PATH ];then
    export LD_LIBRARY_PATH=$root/lib/lib
fi

./$tbin 9999
