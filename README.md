# java-log-generator

## General info
Standalone Java program to generate random log finishing at random period with different log level with unhandled error.

Note: The source code is clone from https://github.com/vspiewak/log-generator and has been modified for testing purpose.

## Setting up API Key

Update API KEY in dd.env
```
DD_SITE=datadoghq.com
DD_API_KEY=<Enter_API_KEY>
````

## Running the program

### Logging configuration

See src/main/resources/logback.xml. Default is raw format

To use JSON format, uncomment the section in logback.xml

### To adjust the number of log record and interval
Update start-app.sh

i.e. To generator 1 random log record every 5 seconds:

java -jar log-generator-\<version\>.jar -n 1 -r 5000
```
  * -logs, -n
       Number of logs to generate
    -repeat, -r
       Repeat every N milliseconds
       Default: 0
    -threads, -t
       Number of threads to use
       Default: 1
```

## Setup Instruction

### Log monitoring only
```
cp docker-compose-logs-only.yaml docker-compose.yaml
docker-compose up --build -d
```
### Trace and Log monitoring

1. Download latest dd-java-agent.jar
wget -O dd-java-agent.jar https://dtdg.co/latest-java-tracer

2. Add the -javaagent JVM argument in start-app.sh (see instruction in bash script)

3. To connect logs and traces
https://docs.datadoghq.com/tracing/connect_logs_and_traces/
https://docs.datadoghq.com/logs/log_collection/java/?tab=logback

Note: If logging is in raw format, add traceid (see instruction in logback.xml)

4. Create and start container

```
cp docker-compose-apm-logs.yaml docker-compose.yaml
docker-compose up --build -d
```

### Check Container Status

* Tail generated logs
```
docker logs -f java-log-generator
```
* Check datadog agent status
```
docker exec -it datadog-agent agent status
```

### Stop and remove container
```
docker-compose down
```
