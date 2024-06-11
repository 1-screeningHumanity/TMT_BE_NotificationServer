package com.notificationserver.application.port.out;

import com.notificationserver.application.port.out.dto.ReadNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;
import java.util.List;

public interface SaveNotificationPort {
	void saveFcmTokenByUuid(SaveNotificationOutDto saveNotificationOutDto);

	void saveNotificationLog(String uuid,String fcmToken, SaveNotificationLogOutDto saveNotificationLogOutDto);

	void updateNotificationLogReadStatus(String uuid, ReadNotificationLogOutDto readNotificationLogOutDto);

	void deleteNotificationLogsByIdsAndUuid(List<Long> notificationLogIds, String uuid);
}
