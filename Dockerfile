FROM openjdk:17
ENV TZ=Asia/Seoul
COPY build/libs/TMT-0.0.1.jar NotificationServer.jar
COPY src/main/resources/fcmStockProjectKey.json /app/resources/fcmStockProjectKey.json
ENTRYPOINT ["java", "-jar", "NotificationServer.jar"]