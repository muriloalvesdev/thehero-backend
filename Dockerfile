FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/bethehero-backend-1.0.0.jar /app/bethehero-backend-1.0.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/bethehero-backend-1.0.0.jar"]
