FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/order-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]
