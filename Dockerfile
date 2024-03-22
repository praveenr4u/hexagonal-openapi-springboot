FROM openjdk:17
MAINTAINER nttdata

ARG JAR_FILE=*.jar
COPY service/build/libs/java-template-service.jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]


