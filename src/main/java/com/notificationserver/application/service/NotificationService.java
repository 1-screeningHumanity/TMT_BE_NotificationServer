package com.notificationserver.application.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.notificationserver.application.port.in.dto.SaveNotificationInDto;
import com.notificationserver.application.port.in.dto.SaveNotificationLogInDto;
import com.notificationserver.application.port.in.usecase.NotificationUseCase;
import com.notificationserver.application.port.out.LoadNotificationPort;
import com.notificationserver.application.port.out.SaveNotificationPort;
import com.notificationserver.application.port.out.dto.ReadNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;
import com.notificationserver.domain.Notification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationUseCase {

	private final SaveNotificationPort saveNotificationPort;
	private final LoadNotificationPort loadNotificationPort;
	private final FirebaseMessaging firebaseMessaging;

	@Override
	public void sendAlarm(String uuid, SaveNotificationLogInDto saveNotificationLogInDto) {
		List<String> fcmTokens = loadNotificationPort.getFcmTokenByUuid(uuid);

		fcmTokens.forEach(fcmToken -> {
			Notification notification = Notification.sendAlarm(fcmToken, saveNotificationLogInDto);

			saveNotificationPort.saveNotificationLog(uuid, fcmToken,
					SaveNotificationLogOutDto.getNotification(notification));

			sendNotificationByFcmToken(notification);
		});
	}

	@Override
	public void saveFcmTokenByUuid(SaveNotificationInDto dto) {
		saveNotificationPort.saveFcmTokenByUuid(
				SaveNotificationOutDto.getSaveNotificationInDto(dto));
	}

	@Override
	public void readAlarm(List<Long> notificationLogIds, String uuid) {


		notificationLogIds.forEach(notificationLogId -> {

			Notification notification = Notification.readAlarm(notificationLogId);

			saveNotificationPort.updateNotificationLogReadStatus(uuid, ReadNotificationLogOutDto.getNoticaiton(notification));
		});
	}

	// fcm 으로 알림 보내기
	private void sendNotificationByFcmToken(Notification notification) {
		com.google.firebase.messaging.Notification fireBaseNotification = com.google.firebase.messaging.Notification.builder()
				.setTitle(notification.getTitle())
				.setBody(notification.getContent())
				.build();

		Message message = Message.builder()
				.setToken(notification.getFcmToken())
				.setNotification(fireBaseNotification)
				.build();

		try {
			firebaseMessaging.send(message);
		} catch (FirebaseMessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
