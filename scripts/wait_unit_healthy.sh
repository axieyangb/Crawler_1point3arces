#!/bin/bash
status=$(curl -I HEAD $1:$2 | awk '{if(NR==1){print $2}}');
while true;
do
  if [ "$status" == "200" ];
  then 
      echo Connected!;
      break;
  fi
  status=$(curl -I HEAD $1:$2 | awk '{if(NR==1){print $2}}');
done;

java -jar $3
