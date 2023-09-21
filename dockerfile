FROM openjdk:11
MAINTAINER mosi
EXPOSE 8080
ADD target/phonebook-0.0.1.jar phonebook-0.0.1.jar
ENTRYPOINT ["java","-jar","/phonebook-0.0.1.jar"]