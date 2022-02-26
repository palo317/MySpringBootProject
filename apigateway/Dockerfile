FROM openjdk:11-jre
WORKDIR /usr/share/app
COPY ./target/apigateway-0.0.1-SNAPSHOT.jar apigateway.jar
EXPOSE 8765
ENTRYPOINT [ "java", "-jar" , "apigateway.jar" ]