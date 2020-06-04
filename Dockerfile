FROM openjdk:jre-alpine
WORKDIR /opt/bethehero/
ADD target/bethehero-backend-0.0.1-SNAPSHOT.jar /opt/bethehero/
EXPOSE 8080
CMD ["java","-jar","/opt/bethehero/bethehero-backend-0.0.1-SNAPSHOT.jar"]