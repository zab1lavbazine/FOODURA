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

# PostgreSQL image
FROM postgres:alpine

# Install PostgreSQL-contrib (if needed)
RUN apk --no-cache add postgresql-contrib

# Create a directory for the data
RUN mkdir -p /var/lib/postgresql/data

# Initialize the PostgreSQL database and create a user
RUN initdb -D /var/lib/postgresql/data --locale en_US.UTF-8 && \
    pg_ctl -D /var/lib/postgresql/data -l /var/lib/postgresql/logfile start && \
    createdb mydatabase && \
    psql -U postgres -d mydatabase -c "CREATE USER baredan WITH PASSWORD 'qwer';" && \
    psql -U postgres -d mydatabase -c "GRANT ALL PRIVILEGES ON DATABASE mydatabase TO bareldan;"

# Actual container
FROM openjdk:17-alpine
ENV ARTIFACT_NAME=Server-Java-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

# Copy the JAR file from the temporary build image to the final image
COPY --from=TEMP_BUILD_IMAGE /home/gradle/src/build/libs/$ARTIFACT_NAME $APP_HOME/$ARTIFACT_NAME

# Expose the default PostgreSQL port (if needed)
EXPOSE 5432

# Start your Spring Boot application
CMD ["java", "-jar", "/usr/app/Server-Java-0.0.1-SNAPSHOT.jar"]

