FROM amazoncorretto:17-alpine-jdk
LABEL authors="mchhikara"
COPY target/learn-spring-0.0.1-SNAPSHOT.jar app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app/app.jar"]
