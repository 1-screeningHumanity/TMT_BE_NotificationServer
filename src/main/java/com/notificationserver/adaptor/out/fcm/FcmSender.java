package com.notificationserver.adaptor.out.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.notificationserver.application.port.out.dto.NotificationSendOutDto;
import com.notificationserver.application.port.out.NotificationSendPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FcmSender implements NotificationSendPort {
	private final FirebaseMessaging firebaseMessaging;

	@Override
	public void sendNotification(NotificationSendOutDto dto) {
		// fcm 으로 알림 보내기
		com.google.firebase.messaging.Notification fireBaseNotification = com.google.firebase.messaging.Notification.builder()
				.setTitle(dto.getTitle())
				.setBody(dto.getContent())
				.build();

		Message message = Message.builder()
				.setToken(dto.getFcmToken())
				.setNotification(fireBaseNotification)
				.build();

		try {
			firebaseMessaging.send(message);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
