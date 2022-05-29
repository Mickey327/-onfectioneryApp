FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","$JAVA_OPTS","-Dserver.port=$PORT","-jar","/app.jar"]
