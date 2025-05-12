FROM maven AS build
# Use official Java image
FROM openjdk:17-jdk-slim

# App will be added to /app directory inside container
WORKDIR /app

RUN mvn clean package


# Copy JAR file (make sure it's built first)
COPY target/meditrack-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
