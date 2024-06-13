package com.notificationserver.application.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.notificationserver.application.port.in.dto.LoadNotificationLogInDto;
import com.notificationserver.application.port.in.dto.NotificationLogCountInDto;
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
	private static final String FCM_EMPTY = "";

	@Override
	public void sendAlarm(SaveNotificationLogInDto dto) {
		List<String> fcmTokens = loadNotificationPort.getFcmTokenByUuid(dto.getUuid());

		// fcm 토큰이 없는 회원은 fcm 전송은 하지 않지만 알림 로그는 저장
		if (fcmTokens.isEmpty()) {
			Notification notification = Notification.sendAlarm(FCM_EMPTY, dto);
			saveNotificationPort.saveNotificationLog(
					SaveNotificationLogOutDto.getNotification(notification));
			return;
		}

		fcmTokens.forEach(fcmToken -> {
			Notification notification = Notification.sendAlarm(fcmToken, dto);
			saveNotificationPort.saveNotificationLog(
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

			Notification notification = Notification.readAlarm(notificationLogId, uuid);

			saveNotificationPort.updateNotificationLogReadStatus(
					ReadNotificationLogOutDto.getNotification(notification));
		});
	}

	@Override
	public List<LoadNotificationLogInDto> getAlarm(String uuid) {
		return Notification.getAlarm(loadNotificationPort.getNotificationLogByUuid(uuid))
				.stream()
				.map(LoadNotificationLogInDto::getNotification)
				.toList();

	}

	@Override
	public NotificationLogCountInDto getAlarmCount(String uuid) {
		return NotificationLogCountInDto.getCount(
				loadNotificationPort.getCountByUuidAndReadStatus(uuid, 44));
	}

	@Override
	public void deleteAlarms(List<Long> notificationLogIds, String uuid) {
		saveNotificationPort.deleteNotificationLogsByIdsAndUuid(
				Notification.deleteAlarms(notificationLogIds), uuid);
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
