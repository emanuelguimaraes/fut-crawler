FROM maven:3.8.2-openjdk-11 AS build
RUN mkdir src
COPY .. src
WORKDIR src
RUN mvn clean install

FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8080

ARG JAR_FILE=/src/target/*.jar
COPY --from=build ${JAR_FILE} app.jar

ARG DEBUG=""

ENTRYPOINT ["java", "${DEBUG}", "-jar","/app.jar"]