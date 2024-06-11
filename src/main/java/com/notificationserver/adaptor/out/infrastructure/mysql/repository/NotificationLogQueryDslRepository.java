package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationLogEntity;
import java.util.List;
import java.util.Optional;

public interface NotificationLogQueryDslRepository {

	Optional<NotificationLogEntity> findNotificationLogByUuidAndId(String uuid, Long notificationLogId);

	List<NotificationLogEntity> findNotificationLogByUuid(String uuid);
}
