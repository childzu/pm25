FROM openjdk:8-jdk-alpine
VOLUME /tmp
MAINTAINER Shavan Tansap <childzu@gmail.com>
ADD target/*.jar app.jar
COPY newrelic newrelic

ENV JAVA_OPTS=""
ENV SPRING_PROFILE="default"

ENTRYPOINT exec java $JAVA_OPTS \
 -javaagent:newrelic/newrelic.jar \
 -Dspring.profiles.active=$SPRING_PROFILE \
 -jar app.jar