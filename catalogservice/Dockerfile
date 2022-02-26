FROM openjdk:11-jre
WORKDIR /usr/share/app
COPY ./target/catalogservice-0.0.1-SNAPSHOT.jar catalogservice.jar
ENTRYPOINT [ "java", "-jar" , "catalogservice.jar" ]