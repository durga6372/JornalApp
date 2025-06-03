# Use JDK 22 for both build and runtime
FROM eclipse-temurin:22-jdk AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY . .
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:22-jre AS runtime

WORKDIR /app
COPY --from=build /app/target/jornalapp1-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082
CMD ["java", "-jar", "app.jar"]
