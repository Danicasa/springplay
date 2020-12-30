FROM openjdk:8-jdk-alpine
RUN addgroup -S projects-eureka && adduser -S admin -G projects-eureka
USER admin:projects-eureka
VOLUME /tmp
#ARG JAR_FILE=/target/*.jar
ADD target/microservicios-eureka-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]