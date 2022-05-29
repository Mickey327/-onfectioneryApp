FROM maven:3.8.4-openjdk-17
WORKDIR /usr/src/java-code
COPY . /usr/src/java-code/
RUN mvn package
ENTRYPOINT ["java", "$JAVA_OPTS","-Dserver.port=$PORT","-jar","/usr/src/java-code/target/confectioneryApp-0.0.1-SNAPSHOT.jar"]
