#!/usr/bin/env bash

PID=$(cat /var/run/quickstart.pid)
kill -9 ${PID}
