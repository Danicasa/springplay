FROM openjdk:8-jdk-alpine
RUN addgroup -S projects-postgresql && adduser -S admin -G projects-postgresql
USER admin:projects-postgresql
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD tarjet/${JAR_FILE} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java JAVA_OPT -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]