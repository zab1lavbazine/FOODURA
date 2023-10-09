s# FROM gradle:jdk17-jammy AS TEMP_BUILD_IMAGE
# ENV APP_HOME=/usr/app/
# WORKDIR $APP_HOME
# COPY build.gradle settings.gradle $APP_HOME

# COPY gradle $APP_HOME/gradle
# COPY --chown=gradle:gradle . /home/gradle/src
# USER root
# RUN chown -R gradle /home/gradle/src

# RUN gradle build || return 0
# COPY . .
# RUN gradle clean build

# # actual container
# FROM openjdk:17-alpine
# ENV ARTIFACT_NAME=pokerstats-0.0.1-SNAPSHOT.jar
# ENV APP_HOME=/usr/app/

# WORKDIR $APP_HOME
# COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

# EXPOSE 8080
# ENTRYPOINT exec java -jar ${ARTIFACT_NAME}






# Temporary build image
FROM gradle:jdk17-jammy AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

# Build the application
RUN gradle build

# Actual container
FROM openjdk:17-alpine
ENV ARTIFACT_NAME=pokerstats-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

# Copy the JAR file from the temporary build image to the final image
COPY --from=TEMP_BUILD_IMAGE /home/gradle/src/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
