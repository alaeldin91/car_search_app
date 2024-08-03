# Use the official OpenJDK 21 image from Oracle
FROM openjdk:22-oracle

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/car_search_service.jar /app/car_search_service.jar

# Make port 8080 available to the world outside this container
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "car_search_service.jar"]