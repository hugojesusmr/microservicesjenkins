FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/inventory-service-1.0.jar
ADD ${JAR_FILE} inventory-service.jar
ENTRYPOINT ["java","-jar","/inventory-service.jar"]