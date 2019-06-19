#!/bin/sh

echo "********************************************************"
echo "Waiting for the service discovery to start on port 8080"
echo "********************************************************"
while ! `nc -z service-discovery 8080 `; do sleep 3; done
echo ">>>>>>>>>>>> Service Discovery has started"

java -Dserver.port=$SERVER_PORT \
     -Dfile.encoding=UTF-8       \
     -jar /usr/local/config-server/config-server.jar
