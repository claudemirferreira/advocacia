FROM adoptopenjdk/openjdk16:alpine-jre

# Copy the JAR file into the container
COPY target/app.jar app.jar

# Expose port 80
EXPOSE 8080

# Define the entry point to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
