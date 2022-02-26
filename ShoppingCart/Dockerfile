FROM openjdk:11-jre
WORKDIR /usr/share/app
COPY ./target/ShoppingCart-0.0.1-SNAPSHOT.jar ShoppingCart.jar
ENTRYPOINT [ "java", "-jar" , "ShoppingCart.jar" ]