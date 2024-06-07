package com.notificationserver.application.service;

import com.notificationserver.application.port.in.dto.SaveNotificationInDto;
import com.notificationserver.application.port.in.usecase.NotificationUseCase;
import com.notificationserver.application.port.out.SaveNotificationPort;
import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationUseCase {

	private final SaveNotificationPort saveNotificationPort;

	@Override
	public void sendAlarm() {
//		send(Notification.sendAlarm());
	}

	@Override
	public void saveFcmTokenByUuid(SaveNotificationInDto dto) {
		saveNotificationPort.saveFcmTokenByUuid(
				SaveNotificationOutDto.getSaveNotificationInDto(dto));
	}
}
