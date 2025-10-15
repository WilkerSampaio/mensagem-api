FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/notificacao-mensagem-0.0.1-SNAPSHOT.jar /app/notificacao-mensagem.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/notificacao-mensagem.jar"]