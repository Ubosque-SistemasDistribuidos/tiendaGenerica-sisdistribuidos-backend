# Stage 1: Build
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY detalle-venta-service/pom.xml detalle-venta-service/pom.xml
COPY detalle-venta-service/src ./detalle-venta-service/src

RUN cd detalle-venta-service && mvn clean package -DskipTests -q && cd ..

# Stage 2: Runtime
FROM eclipse-temurin:11

WORKDIR /app

COPY --from=builder /app/detalle-venta-service/target/*.jar app.jar

EXPOSE 8094

ENTRYPOINT ["java", "-jar", "app.jar"]
