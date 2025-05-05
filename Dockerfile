# Use a base image with JDK
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the code
COPY . .

# Package the application
RUN ./mvnw clean install

# Run the application
CMD ["java", "-jar", "target/jornalapp1-0.0.1-SNAPSHOT.jar"]
