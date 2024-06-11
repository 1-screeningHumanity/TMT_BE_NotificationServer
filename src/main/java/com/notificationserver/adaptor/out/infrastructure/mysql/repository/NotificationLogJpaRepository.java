package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationLogJpaRepository extends JpaRepository<NotificationLogEntity, Long>, NotificationLogQueryDslRepository {

}
