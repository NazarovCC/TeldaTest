FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true


FROM amazoncorretto:17-alpine-jdk
ARG JAR_FILE=target/Telda_Regions-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY --from=builder /app/${JAR_FILE} app.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "app.jar"]