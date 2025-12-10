FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

# Build the application without running tests to avoid DB dependency during image build
RUN mvn -B --no-transfer-progress -DskipTests clean package


# Use JRE 17 to match the project's target version
FROM eclipse-temurin:17-jre AS runner

WORKDIR /app

# FIX 3: Corrected spelling from 'SNAPSOT' to 'SNAPSHOT'
COPY --from=builder /app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

# Match the service port defined in application.properties
EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]