#!/bin/bash

# Start log-generator application, 1 log record every 5 seconds
java -jar ${APP_HOME}/log-generator.jar -n 1 -r 5000

# Start log-generator application with dd-java-agent tracer
# java -javaagent:${APP_HOME}/dd-java-agent.jar -XX:FlightRecorderOptions=stackdepth=256 -jar ${APP_HOME}/log-generator.jar -n 1 -r 5000
