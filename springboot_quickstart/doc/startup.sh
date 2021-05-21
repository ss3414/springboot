#!/usr/bin/env bash

java -jar springboot_quickstart-1.0.jar >/dev/null 2>&1 &
echo $! >/var/run/quickstart.pid # 将进程号输出到文件
