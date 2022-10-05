FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8001
ARG JAR_FILE=build/libs/gateway-service-1.0.jar
ADD ${JAR_FILE} gateway-service.jar
ENTRYPOINT ["java","-jar","/gateway-service.jar"]