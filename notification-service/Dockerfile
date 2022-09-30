FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/notification-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} notification-service.jar
ENTRYPOINT ["java","-jar","/notification-service.jar"]