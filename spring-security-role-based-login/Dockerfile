# Step 1: Use Maven to build the JAR
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the JAR with a slim JDK
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (Render sets PORT dynamically, we just document it here)
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]
