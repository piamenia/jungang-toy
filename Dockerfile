FROM openjdk:13-jdk-alpine
ARG JAR_FILE=/build/libs/*.jar
COPY $JAR_FILE app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "-Dserver.port=8081", "/app.jar"]
