#!/bin/sh
java -Dserver.port=$SERVER_PORT \
     -Dfile.encoding=UTF-8       \
     -jar /usr/local/config-server/config-server.jar
