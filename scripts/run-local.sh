#!/bin/bash
export OAUTH_VALUE=9a597bPpQ%2BWfCOWpN6kYdzwl%2FKiL5PyqKb40256n0Nwi6hMzqj8P1nnYQBIk%2Fqfi8dgthDLi8Yzm%2B%2FV337bkvDXeFqY
export SALT_VALUE=V0h0TUbW
export EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8761/eureka

export JAVA_HOME=`/usr/libexec/java_home -v 1.8.0_152`;
ps -ef | grep 'java' | grep 'target/' | awk '{print $2}' | xargs kill 

cd ../eureka-server/
nohup java -jar target/eureka-server-0.0.1-SNAPSHOT.jar > ../scripts/eureka-server.log 2>&1 &

status=$(curl -I HEAD localhost:8761 | awk '{if(NR==1){print $2}}');
while true;
do
  if [ "$status" == "200" ];
  then
      echo Connected!;
      break;
  fi
  status=$(curl -I HEAD localhost:8761 | awk '{if(NR==1){print $2}}');
done;

cd ../crawler-service

nohup java -jar target/crawler-service-0.0.1-SNAPSHOT.jar > ../scripts/crawler-service.log 2>&1 &

cd ../crawler-worker
nohup java -jar target/crawler-worker-0.0.1-SNAPSHOT.jar > ../scripts/crawler-worker.log 2>&1 &



