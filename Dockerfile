FROM maven:3.9.6-eclipse-temurin-21-alpine as build
COPY /src /betterme/src
COPY pom.xml /betterme/pom.xml
COPY lombok.config /betterme/lombok.config
WORKDIR /betterme
RUN mvn clean -DskipTests=true package

FROM eclipse-temurin:21.0.2_13-jre-alpine
WORKDIR /betterme
COPY --from=build /betterme/target/*.jar /betterme.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/betterme.jar"]