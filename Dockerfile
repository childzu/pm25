FROM openjdk:8-jdk-alpine

LABEL maintainer="childzu@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/pm25-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} pm25.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/pm25.jar"]
