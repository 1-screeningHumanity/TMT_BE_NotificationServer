package com.notificationserver.application.port.out;

import com.notificationserver.application.port.out.dto.SaveNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;

public interface SaveNotificationPort {
	void saveFcmTokenByUuid(SaveNotificationOutDto saveNotificationOutDto);

	void saveNotificationLog(String uuid,String fcmToken, SaveNotificationLogOutDto saveNotificationLogOutDto);
}
