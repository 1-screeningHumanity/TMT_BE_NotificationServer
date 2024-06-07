package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {

}
