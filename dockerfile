FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/demosclient-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_inventario.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app_inventario.jar"]