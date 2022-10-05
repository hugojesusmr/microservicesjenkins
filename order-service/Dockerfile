FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/order-service-1.0.jar
ADD ${JAR_FILE} order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]
