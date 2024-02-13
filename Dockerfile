FROM openjdk:latest
COPY /home/runner/work/semGroup17/semGroup17/target/semGroup17-0.1.0.1-jar-with-dependencies.jar /var/tmp/
WORKDIR /var/tmp
ENTRYPOINT ["java", "-jar", "semGroup17-0.1.0.1-jar-with-dependencies.jar"]