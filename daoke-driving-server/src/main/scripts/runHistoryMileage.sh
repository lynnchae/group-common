#!/bin/sh

echo `date`

LIB_PATH="./lib"
CLASS_PATH=""

JAVA_OPTS="-Xms512m -Xmx768m -Xmn256m -XX:PermSize=128M -XX:MaxPermSize=128m -XX:ParallelGCThreads=4 -XX:+UseConcMarkSweepGC  -XX:+CMSClassUnloadingEnabled  -XX:CMSInitiatingOccupancyFraction=80 -XX:+UseParNewGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError"

for jar in `ls $LIB_PATH/*.jar`
do
   CLASS_PATH=$CLASS_PATH":"$jar
done

echo "CLASS_PATH:" "$CLASS_PATH"

nohup java -classpath "$CLASS_PATH" $JAVA_OPTS me.daoke.driving.RunHistoryMileageMain &