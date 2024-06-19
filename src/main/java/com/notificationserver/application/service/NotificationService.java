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


	@Override
	public void sendAlarm(SaveNotificationLogInDto dto) {
		List<String> fcmTokens = loadNotificationPort.getFcmTokenByUuid(dto.getUuid());

		Notification notification = Notification.sendAlarm(dto);

		saveAndSendAlarm(notification, fcmTokens);
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

	private void saveAndSendAlarm(Notification notification, List<String> fcmTokens) {
		// fcm 관계 없이 알림 로그 저장
		saveNotificationPort.saveNotificationLog(
				SaveNotificationLogOutDto.getNotification(notification));

		// fcm token이 있는 경우 알림 메시지 전송
		fcmTokens.forEach(fcmToken -> {
			// 알림 메시지 전송
			notificationSendPort.sendNotification(
					NotificationSendOutDto.getNotification(notification, fcmToken));
		});
	}
}
