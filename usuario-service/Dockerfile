# Stage 1: Build
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY usuario-service/pom.xml usuario-service/pom.xml
COPY usuario-service/src ./usuario-service/src

RUN cd usuario-service && mvn clean package -DskipTests -q && cd ..

# Stage 2: Runtime
FROM eclipse-temurin:11

WORKDIR /app

COPY --from=builder /app/usuario-service/target/*.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
