FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8000
ARG JAR_FILE=build/libs/config-server-1.0.jar
ADD ${JAR_FILE} config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]