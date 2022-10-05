FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8761
ARG JAR_FILE=build/libs/discovery-service-1.0.jar
ADD ${JAR_FILE} discovery-service.jar
ENTRYPOINT ["java","-jar","/discovery-service.jar"]