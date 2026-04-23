# Stage 1: Build
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY cliente-service/pom.xml cliente-service/pom.xml
COPY cliente-service/src ./cliente-service/src

RUN cd cliente-service && mvn clean package -DskipTests -q && cd ..

# Stage 2: Runtime
FROM eclipse-temurin:11

WORKDIR /app

COPY --from=builder /app/cliente-service/target/*.jar app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]
