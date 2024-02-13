FROM openjdk:latest
RUN pwd && ls -la
COPY ./target/semGroup17-0.1.0.1-jar-with-dependencies.jar /var/tmp/
WORKDIR /var/tmp
ENTRYPOINT ["java", "-jar", "semGroup17-0.1.0.1-jar-with-dependencies.jar"]