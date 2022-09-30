FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/inventory-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} inventory-service.jar
ENTRYPOINT ["java","-jar","/inventory-service.jar"]