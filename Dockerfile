FROM openjdk:17-alpine
EXPOSE 8081
ADD target/Design-Pattern-0.0.1-SNAPSHOT.jar Design-Pattern-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/Design-Pattern-0.0.1-SNAPSHOT.jar" ]