# Code gen
FROM ivvq-2020-project-rocket_testing-env AS codegen
WORKDIR /app
ARG appversion
RUN mvn package -DskipTests && cp /app/target/ivvq-project-$appversion.jar ./myApp.jar

# Front
FROM node:latest AS front
WORKDIR /src
COPY front/ivvq-project/ .
COPY --from=codegen /front/ivvq-project/src/api/endpoints.ts src/api/
RUN npm install && npm run build

# Builder
FROM codegen AS builder
COPY --from=front /src/dist/ /app/src/main/resources/public/
ARG appversion
RUN mvn package -DskipTests && cp /app/target/ivvq-project-$appversion.jar ./myApp.jar

# Runtime
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /app/myApp.jar ./myApp.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod", "-jar", "./myApp.jar"]
