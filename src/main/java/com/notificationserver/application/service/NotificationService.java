package com.notificationserver.application.service;

import com.notificationserver.application.port.in.dto.LoadNotificationLogInDto;
import com.notificationserver.application.port.in.dto.NotificationLogCountInDto;
import com.notificationserver.application.port.in.dto.SaveNotificationInDto;
import com.notificationserver.application.port.in.dto.SaveNotificationLogInDto;
import com.notificationserver.application.port.in.usecase.NotificationUseCase;
import com.notificationserver.application.port.out.LoadNotificationPort;
import com.notificationserver.application.port.out.dto.NotificationSendOutDto;
import com.notificationserver.application.port.out.NotificationSendPort;
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
	private final NotificationSendPort notificationSendPort;
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

			// 알림 메시지 전송
			notificationSendPort.sendNotification(
					NotificationSendOutDto.getNotification(notification));
		});
	}

	@Override
	public void saveFcmTokenByUuid(SaveNotificationInDto dto) {
		saveNotificationPort.saveFcmTokenByUuid(
				SaveNotificationOutDto.getSaveNotificationInDto(dto));
	}

	@Override
	public void deleteFcmToken(String uuid, String fcmToken) {
		saveNotificationPort.deleteByUuidAndFcmToken(uuid, fcmToken);
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
}
