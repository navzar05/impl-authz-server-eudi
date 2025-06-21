FROM openjdk:17-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

COPY target/*.jar app.jar

RUN chown spring:spring app.jar

USER spring:spring

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]