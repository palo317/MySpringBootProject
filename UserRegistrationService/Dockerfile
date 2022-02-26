FROM openjdk:11-jre
WORKDIR /usr/share/app
COPY ./target/UserRegistrationService-0.0.1-SNAPSHOT.jar UserRegistrationService.jar
ENTRYPOINT [ "java", "-jar" , "UserRegistrationService.jar" ]