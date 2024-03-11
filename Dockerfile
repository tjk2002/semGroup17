FROM openjdk:latest
COPY target/semGroup17-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semGroup17-0.1.0.2-jar-with-dependencies.jar"]