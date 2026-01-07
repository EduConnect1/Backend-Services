# Use Java 21
FROM eclipse-temurin:21-jdk-alpine

# Create working directory
WORKDIR /app


COPY target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]
