FROM maven:3.5-jdk-8
FROM java:8
WORKDIR /app
COPY target/*.jar /app/running-in-docker.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","running-in-docker.jar"]
