# Stage 1: Build with Java 21 and Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime with Java 21
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar quiz.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "quiz.jar"]