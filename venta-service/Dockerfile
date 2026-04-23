# Stage 1: Build
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY venta-service/pom.xml venta-service/pom.xml
COPY venta-service/src ./venta-service/src

RUN cd venta-service && mvn clean package -DskipTests -q && cd ..

# Stage 2: Runtime
FROM eclipse-temurin:11

WORKDIR /app

COPY --from=builder /app/venta-service/target/*.jar app.jar

EXPOSE 8093

ENTRYPOINT ["java", "-jar", "app.jar"]
