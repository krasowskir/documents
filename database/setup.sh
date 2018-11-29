#!/bin/bash
### OHNE SUDO!!! ###


host=127.0.0.1
user=postgres
port=5432
image=postgres_with_scripts

docker build --no-cache -t $image .
container=$(docker run -d -p $port:$port postgres_with_scripts:latest)
echo $container
sleep 5
docker exec  $container /bin/bash "createDb.sh" $host  $user


