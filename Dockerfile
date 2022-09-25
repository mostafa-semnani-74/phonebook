FROM openjdk:11
LABEL maintainer="mostafa semnani"
ADD target/phonebook.jar phonebook.jar
ENTRYPOINT ["java", "-jar", "phonebook.jar"]