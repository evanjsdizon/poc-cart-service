FROM eclipse-temurin:17.0.6_10-jre-alpine
WORKDIR /tmp
COPY target/poc-cart-service-0.0.1-SNAPSHOT.jar poc-cart-service.jar
EXPOSE 8030 8040
CMD [ "java", "-jar",  "poc-cart-service.jar"]