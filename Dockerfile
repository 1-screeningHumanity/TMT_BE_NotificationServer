FROM openjdk:17
ENV TZ=Asia/Seoul
COPY build/libs/TMT-0.0.1.jar NotificationServer.jar
ENTRYPOINT ["java", "-jar", "NotificationServer.jar"]