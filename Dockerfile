FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/bethehero-backend-*.jar bethehero.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./