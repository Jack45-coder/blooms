FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests
ARG SPRING_DATA_MONGODB_URI
ENV SPRING_DATA_MONGODB_URI=${SPRING_DATA_MONGODB_URI}
CMD ["sh", "-c", "java -Dspring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI} -jar target/blooms-0.0.1-SNAPSHOT.jar"]