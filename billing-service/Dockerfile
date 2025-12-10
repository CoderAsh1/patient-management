FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package


# FIX 1 & 2: Changed to a valid image provider and matched version 21
FROM eclipse-temurin:21-jre AS runner

WORKDIR /app

# FIX 3: Corrected spelling from 'SNAPSOT' to 'SNAPSHOT'
COPY --from=builder /app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 9090
EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]