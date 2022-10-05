FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/notification-service-1.0.jar
ADD ${JAR_FILE} notification-service.jar
ENTRYPOINT ["java","-jar","/notification-service.jar"]