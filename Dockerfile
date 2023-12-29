# 1st stage
FROM openjdk:17-jdk-alpine3.13 AS build

WORKDIR /app

COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY src /app/src

# Copy the gradlew script
COPY gradlew /app/

# Ensure Gradle Wrapper is executable
RUN chmod +x ./gradlew

# Run Gradle build
RUN ./gradlew build

# 2nd stage
FROM openjdk:17-jdk-alpine3.13

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
