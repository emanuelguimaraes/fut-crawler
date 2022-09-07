FROM maven:3.8.3-openjdk-17 as build
RUN mkdir players
COPY . players
WORKDIR players
RUN mvn clean install

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /players/insfrastructure/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/app.jar"]