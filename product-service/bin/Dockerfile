FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/product-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]