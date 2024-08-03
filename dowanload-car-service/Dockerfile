# Use the official OpenJDK 21 image from Oracle
FROM openjdk:22-oracle

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/dowanload_car_service.jar /app/dowanload_car_service.jar

# Make port 8080 available to the world outside this container
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "dowanload_car_service.jar"]