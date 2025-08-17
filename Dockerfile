FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test

CMD ["java", "-Dserver.port=${PORT}", "-jar", "build/libs/*.jar"]