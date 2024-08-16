FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/demosclient-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demosclient-0.0.1-SNAPSHOT.jar"]