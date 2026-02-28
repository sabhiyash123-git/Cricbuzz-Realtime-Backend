# Step 1: Use a stable and lightweight JDK image (Alpine is very small)
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file from the target folder to the container
# Maven build 'target' folder mein 'demo-0.0.1-SNAPSHOT.jar' banata hai
COPY target/*.jar app.jar

# Step 4: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]