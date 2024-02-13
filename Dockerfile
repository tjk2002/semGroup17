FROM openjdk:latest
RUN mkdir -p /var/tmp
RUN mvn compile
COPY target/semGroup17-0.1.0.1-jar-with-dependencies.jar /var/tmp/semGroup17-0.1.0.1-jar-with-dependencies.jar
WORKDIR /var/tmp
ENTRYPOINT ["java", "-jar", "semGroup17-0.1.0.1-jar-with-dependencies.jar"]