FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
ENV PORT=8080
ENV JAVA_TOOL_OPTIONS="-Xmx256m -Xss512k -XX:MaxRAM=400m"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
