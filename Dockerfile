FROM gradle:8.5-jdk21 AS build

WORKDIR /app

# Copy only gradle wrapper files first
COPY gradlew .
COPY gradle gradle

# Give execution permission to gradlew
RUN chmod +x gradlew

# Copy the rest of the project
COPY . .

# Build without tests
RUN ./gradlew clean build -x test