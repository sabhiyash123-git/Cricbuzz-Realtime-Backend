# Step 1: Use a lightweight Java runtime
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file built by Maven into the container
# Maven build 'target' folder mein 'demo-0.0.1-SNAPSHOT.jar' banata hai
COPY target/*.jar app.jar

# Step 4: Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]