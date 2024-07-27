# Start with Azul Zulu OpenJDK 21 base image
FROM azul/zulu-openjdk:21

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY api/build/libs/api-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
