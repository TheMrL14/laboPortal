#### Stage 1: Build the application
FROM maven:3.6.3-jdk-11 as build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

#### Stage 2: A minimal docker image with command to run the app
FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine
COPY --from=build /usr/src/app/target/laboportal-backend.jar /home/app/laboportal-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/laboportal-backend.jar"]