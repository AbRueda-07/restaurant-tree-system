FROM maven:3.9.8-eclipse-temurin-17 AS build

WORKDIR /workspace

COPY pom.xml .
COPY tree-engine/pom.xml tree-engine/pom.xml
COPY app/pom.xml app/pom.xml

RUN mvn dependency:go-offline

COPY tree-engine/src tree-engine/src
COPY app/src app/src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /workspace/app/target/app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]