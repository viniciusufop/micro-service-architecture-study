#!/bin/sh

echo "********************************************************"
echo "Waiting for the database server to start on port 3306"
echo "********************************************************"
while ! `nc -z database 3306`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

java -Dserver.port=$SERVER_PORT \
    -Dfile.encoding=UTF-8       \
    -jar /usr/local/estoque/estoque@.jar