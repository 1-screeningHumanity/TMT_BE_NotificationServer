package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long>, NotificationQueryDslRepository {

	Optional<NotificationEntity> findByUuidAndFcmToken(String uuid, String fcmToken);
}
