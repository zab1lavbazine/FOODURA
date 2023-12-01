#first stage
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app
#copy all for build
COPY . .
#build
RUN ./gradlew build





#second stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]