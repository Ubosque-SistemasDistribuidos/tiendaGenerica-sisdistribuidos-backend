# Stage 1: Build
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY proveedor-service/pom.xml proveedor-service/pom.xml
COPY proveedor-service/src ./proveedor-service/src

RUN cd proveedor-service && mvn clean package -DskipTests -q && cd ..

# Stage 2: Runtime
FROM eclipse-temurin:11

WORKDIR /app

COPY --from=builder /app/proveedor-service/target/*.jar app.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar", "app.jar"]
