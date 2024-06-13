package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import java.util.List;

public interface NotificationQueryDslRepository {
	List<String> findFcmTokensByUuid(String uuid);

	void deleteNotificationByUuidAndFcmToken(String uuid, String fcmToken);
}
