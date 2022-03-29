# Build stage
FROM maven:3.8.4-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine
# Install Required Packages
RUN apk add --update-cache curl bash net-tools && rm -rf /var/cache/apk/*
# Create a Application Home directory to put the application artifacts.
ENV APP_HOME /app
RUN mkdir -p ${APP_HOME}
COPY --from=build /home/app/target/log-generator-0.0.1-SNAPSHOT.jar ${APP_HOME}/log-generator.jar

# Add the application startup script to the container.
COPY start-app.sh ${APP_HOME}

# Add executable permission to the application startup script.
RUN chmod +x ${APP_HOME}/start-app.sh

# Run the application by invoking the start script
CMD "${APP_HOME}/start-app.sh"
