FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests
ENV SPRING_DATA_MONGODB_URI=${SPRING_DATA_MONGODB_URI}
CMD java -jar target/blooms-0.0.1-SNAPSHOT.jar