package com.notificationserver.application.port.in.usecase;

import com.notificationserver.application.port.in.dto.SaveNotificationInDto;
import com.notificationserver.application.port.in.dto.SaveNotificationLogInDto;

public interface NotificationUseCase {
	void sendAlarm(String uuid, SaveNotificationLogInDto saveNotificationLogInDto);

	void saveFcmTokenByUuid(SaveNotificationInDto saveNotificationInDto);
}