package com.notificationserver.application.port.out;

import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;

public interface SaveNotificationPort {
	void saveFcmTokenByUuid(SaveNotificationOutDto saveAlarmDto);
}
