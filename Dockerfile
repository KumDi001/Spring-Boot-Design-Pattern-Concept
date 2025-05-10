FROM openjdk:17-alpine
EXPOSE 8081
ADD target/practice-demo.jar practice-demo.jar
ENTRYPOINT [ "java","-jar","/practice-demo.jar" ]