package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationLogEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationLogJpaRepository extends JpaRepository<NotificationLogEntity, Long>, NotificationLogQueryDslRepository {
	Optional<NotificationLogEntity> findByUuidAndId(String uuid, Long id);
	List<NotificationLogEntity> findByUuidOrderByNotificationLogCreateAtDesc(String uuid);
}
