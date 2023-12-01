

#1 stage
FROM openjdk:17-jdk-alpine3.13 AS build

WORKDIR /app

COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
COPY src /app/src

RUN ./gradlew build


#2 stage
FROM openjdk:17-jdk-alpine3.13
WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]