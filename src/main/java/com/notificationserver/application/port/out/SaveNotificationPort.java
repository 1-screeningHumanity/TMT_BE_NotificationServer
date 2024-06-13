package com.notificationserver.application.port.out;

import com.notificationserver.application.port.out.dto.ReadNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;
import java.util.List;

public interface SaveNotificationPort {
	void saveFcmTokenByUuid(SaveNotificationOutDto saveNotificationOutDto);

	void deleteByUuidAndFcmToken(String uuid, String fcmToken);

	void saveNotificationLog(SaveNotificationLogOutDto saveNotificationLogOutDto);

	void updateNotificationLogReadStatus(ReadNotificationLogOutDto readNotificationLogOutDto);

	void deleteNotificationLogsByIdsAndUuid(List<Long> notificationLogIds, String uuid);
}
