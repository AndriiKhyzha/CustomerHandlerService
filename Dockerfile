# https://spring.io/guides/topicals/spring-boot-docker/

FROM openjdk:17-jdk-slim-buster

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=customer-handler-service-boot/target/customer-handler-service-exec.jar

COPY ${JAR_FILE} customer-handler-service-exec.jar

ENTRYPOINT ["java","-jar","/customer-handler-service-exec.jar"]