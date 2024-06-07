package com.notificationserver.adaptor.out.infrastructure.mysql.persistance;

import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationEntity;
import com.notificationserver.adaptor.out.infrastructure.mysql.repository.NotificationJpaRepository;
import com.notificationserver.application.port.out.SaveNotificationPort;
import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationAdaptor implements SaveNotificationPort{
	private final NotificationJpaRepository notificationJpaRepository;

	@Override
	public void saveFcmTokenByUuid(SaveNotificationOutDto saveNotificationOutDto) {
		notificationJpaRepository.save(NotificationEntity.toEntityFrom(saveNotificationOutDto));
	}
}
