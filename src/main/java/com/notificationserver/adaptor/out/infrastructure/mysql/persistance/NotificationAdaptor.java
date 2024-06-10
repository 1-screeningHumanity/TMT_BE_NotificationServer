package com.notificationserver.adaptor.out.infrastructure.mysql.persistance;

import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationEntity;
import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationLogEntity;
import com.notificationserver.adaptor.out.infrastructure.mysql.repository.NotificationJpaRepository;
import com.notificationserver.adaptor.out.infrastructure.mysql.repository.NotificationLogJpaRepository;
import com.notificationserver.application.port.out.LoadNotificationPort;
import com.notificationserver.application.port.out.SaveNotificationPort;
import com.notificationserver.application.port.out.dto.ReadNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationLogOutDto;
import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;
import com.notificationserver.global.common.exception.CustomException;
import com.notificationserver.global.common.response.BaseResponseCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NotificationAdaptor implements SaveNotificationPort, LoadNotificationPort {

	private final NotificationJpaRepository notificationJpaRepository;
	private final NotificationLogJpaRepository notificationLogJpaRepository;

	@Override
	@Transactional
	public void saveFcmTokenByUuid(SaveNotificationOutDto saveNotificationOutDto) {
		notificationJpaRepository.save(NotificationEntity.toEntityFrom(saveNotificationOutDto));
	}

	@Override
	@Transactional
	public void saveNotificationLog(
			String uuid,
			String fcmToken,
			SaveNotificationLogOutDto saveNotificationLogOutDto) {

		NotificationEntity notificationEntity = notificationJpaRepository.findByUuidAndFcmToken(
						uuid, fcmToken)
				.orElseThrow(() -> new IllegalArgumentException("조회된 알림 정보가 없습니다."));

		notificationLogJpaRepository.save(
				NotificationLogEntity.toEntityFrom(notificationEntity, saveNotificationLogOutDto));
	}

	@Override
	@Transactional
	public void updateNotificationLogReadStatus(
			String uuid,
			ReadNotificationLogOutDto dto
	) {

		NotificationLogEntity notificationLogEntity = notificationLogJpaRepository.findNotificationLogByUuidAndId(
						uuid, dto.getNotificationId())
				.orElseThrow(() -> new CustomException(
						BaseResponseCode.NOT_UPDATE_NOTIFICATION_LOG_READ_STATUS));

		notificationLogJpaRepository.save(
				NotificationLogEntity.updateReadStatus(notificationLogEntity, dto.getReadStatus()));
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getFcmTokenByUuid(String uuid) {
		return notificationJpaRepository.findFcmTokensByUuid(uuid);
	}
}
