
# Builder
FROM maven:3-jdk-8 AS builder
WORKDIR /app
COPY back/pom.xml .
RUN mvn dependency:go-offline -B
COPY back/src src
ARG appversion
RUN mvn package -DskipTests && cp /app/target/ivvq-project-$appversion.jar ./myApp.jar

# Runtime
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /app/myApp.jar ./myApp.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","./myApp.jar"]
