FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/productsapi-0.0.1-SNAPSHOT.jar /app/products_api.jar
COPY .env /app/.env
ARG JAR_FILE=*jar
ENTRYPOINT ["java","-jar","products_api.jar"]
