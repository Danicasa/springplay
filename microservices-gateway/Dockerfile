FROM openjdk:8-jdk-alpine
RUN addgroup -S projects-gateway && adduser -S admin -G projects-gateway
USER admin:projects-gateway
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD tarjet/${JAR_FILE} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java JAVA_OPT -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]