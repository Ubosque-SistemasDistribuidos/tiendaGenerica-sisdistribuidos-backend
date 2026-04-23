# Stage 1: Build
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app

# Copy parent pom
COPY pom.xml .

# Copy service pom
COPY backend-service/pom.xml backend-service/pom.xml

# Copy source code
COPY backend-service/src ./backend-service/src

# Build
RUN cd backend-service && mvn clean package -DskipTests -q && cd ..

# Stage 2: Runtime
FROM eclipse-temurin:11

WORKDIR /app

# Copy JAR from builder
COPY --from=builder /app/backend-service/target/*.jar app.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "app.jar"]
