FROM openjdk:11-jre
WORKDIR /usr/share/app
COPY ./target/servicediscovery-0.0.1-SNAPSHOT.jar servicediscovery.jar
EXPOSE 8761
ENTRYPOINT [ "java", "-jar" , "servicediscovery.jar" ]