package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import java.util.List;

public interface NotificationLogQueryDslRepository {
	void deleteNotificationLogsByIdsAndUuid(List<Long> notificationLogIds, String uuid);

	long getCountByUuidAndReadStatus(String uuid, Integer readStatus);
}
