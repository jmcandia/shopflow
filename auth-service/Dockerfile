# Etapa de construcción
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:resolve

COPY src ./src

RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:21-ea-27-slim-bullseye

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]