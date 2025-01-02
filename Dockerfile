FROM openjdk:21-jdk
MAINTAINER mosi
EXPOSE 8081
ADD target/phonebook-0.0.1.jar phonebook-0.0.1.jar
ENTRYPOINT ["java","-jar","/phonebook-0.0.1.jar"]