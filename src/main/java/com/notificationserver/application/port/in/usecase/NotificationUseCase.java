package com.notificationserver.application.port.in.usecase;

import com.notificationserver.application.port.in.dto.SaveNotificationInDto;

public interface NotificationUseCase {
	void sendAlarm();

	void saveFcmTokenByUuid(SaveNotificationInDto saveAlarmDto);
}