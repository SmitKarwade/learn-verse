# Use Eclipse Temurin JDK 21 as base (lightweight)
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Copy Gradle wrapper and permissions
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

# Copy everything and build the JAR
COPY . .
RUN ./gradlew clean bootJar -x test

# -------------------- #
# Run stage
# -------------------- #
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

#Run
ENTRYPOINT ["java", "-jar", "app.jar"]