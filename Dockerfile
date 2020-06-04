FROM openjdk:8
ADD target/bethehero-backend-0.0.1-SNAPSHOT.jar /opt/bethehero/
WORKDIR "/opt/bethehero/"
CMD ["java","-jar","bethehero-backend-0.0.1-SNAPSHOT.jar"]