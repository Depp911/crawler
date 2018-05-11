#!/usr/bin/env bash

THIS="$0"
while [ -h "$THIS" ]; do
    ls=`ls -ld "$THIS"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '.*/.*' > /dev/null; then
    THIS="$link"
    else
    THIS=`dirname "$THIS"`/"$link"
    fi
done
THIS_DIR=`dirname "$THIS"`
HOME=`cd "$THIS_DIR/.." ; pwd`
export HOME

if [ "$JAVA_HOME" = "" ]; then
  JAVA="java"
else
  JAVA="$JAVA_HOME/bin/java"
fi;

SERVER="-server"
if hash dir 2>/dev/null; then
    SERVER=""
fi

JAVA_HEAP_MAX=-Xmx512m

# assertion
JAVA_ASSERT="-ea"
while true; do
    if [ "$1" = "-noassert" ] ; then
      JAVA_ASSERT="-da"
      shift;
    # remote debugging
    elif [ "$1" = "-jdwp" ] ; then
      JAVA="$JAVA -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=0.0.0.0:8001"
      shift;
    # CPU profiling
    elif [ "$1" = "-cpu" ] ; then
      JAVA="$JAVA -agentlib:hprof=cpu=samples,depth=20"
      shift;
    # Heap size
    elif [ "$1" = "-heap" ] ; then
      shift;
      JAVA_HEAP_MAX="-Xmx$1"
      shift;
    # Do lock contention profiling
    elif [ "$1" = "-lock" ] ; then
      JAVA="$JAVA -agentlib:hprof=monitor=y"
      shift;
    # other options
    elif [ "$1" = "-J" ] ; then
      shift;
      JAVA="$JAVA $1"
      shift;
    elif [ ! `expr "$1" : "-D.*"` = "0" ] ; then
      JAVA="$JAVA $1"
      shift;
    elif [ ! `expr "$1" : "-X.*"` = "0" ] ; then
      JAVA="$JAVA $1"
      shift;
    else
      break;
    fi
done

CLASSPATH=$HOME
CLASSPATH=${CLASSPATH}:$HOME/target/classes
CLASSPATH=${CLASSPATH}:$HOME/target/lib
for f in $HOME/target/lib/*.jar; do
  CLASSPATH=${CLASSPATH}:$f;
done

# figure out which class to run
CLASS=org.jayne.crawler.tool.ToolMain

EXEC_CMD="$JAVA $SERVER $JAVA_HEAP_MAX $JAVA_ASSERT -Dodis.home=$HOME -Dpid=$$ -classpath $CLASSPATH $CLASS $@"
exec $EXEC_CMD