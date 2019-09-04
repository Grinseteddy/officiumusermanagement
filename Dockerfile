FROM openjdk:latest
COPY ./target/usermangement-0.0.1.jar /usr/src/usermangement-0.0.1.jar
EXPOSE 5050
CMD java -jar /usr/src/usermangement-0.0.1.jar