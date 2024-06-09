# https://spring.io/guides/topicals/spring-boot-docker/

FROM openjdk:17-jdk-slim-buster

VOLUME /tmp
# Expose the application port
EXPOSE 8080
# Copy the JAR package into the image
ARG JAR_FILE=customer-handler-service-boot/target/customer-handler-service-exec.jar

COPY ${JAR_FILE} customer-handler-service-exec.jar
# Run the App
ENTRYPOINT ["java","-jar","/customer-handler-service-exec.jar"]