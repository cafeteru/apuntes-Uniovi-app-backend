FROM maven:3.8.1-jdk-11 as builder

COPY . /app

RUN cd app; mvn clean package

CMD java -jar /app/target/apuntesuniovi-0.0.1-SNAPSHOT.jar
