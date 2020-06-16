#FROM alpine:latest
#RUN apt-get update

FROM openjdk:8
ADD target/system-0.0.1-SNAPSHOT.jar system-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "system-0.0.1-SNAPSHOT.jar"]