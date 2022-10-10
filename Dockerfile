FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/ms-user*.jar /app-ms-user.jar
CMD ["java","-jar","/app-ms-user.jar","--spring.profiles.active=prod"]
EXPOSE 8082