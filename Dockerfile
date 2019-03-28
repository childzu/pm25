FROM openjdk:8-jdk-slim
LABEL maintainer="childzu@gmail.com"
ENV PORT 8080
ENV CLASSPATH /opt/lib
EXPOSE 8080
ARG JAR_FILE=target/pm25-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/app.jar
WORKDIR /opt
CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]