FROM maven:3-eclipse-temurin-21-jammy AS build

WORKDIR /app

COPY . .

RUN mvn package

FROM tomcat:11.0-jdk21-temurin-jammy

RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war