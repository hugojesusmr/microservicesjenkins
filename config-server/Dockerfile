FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8000
ARG JAR_FILE=build/libs/config-server-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]