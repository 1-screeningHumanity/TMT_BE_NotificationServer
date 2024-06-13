package com.notificationserver.adaptor.out.infrastructure.mysql.persistance;

import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationEntity;
import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationLogEntity;
import com.notificationserver.adaptor.out.infrastructure.mysql.repository.NotificationJpaRepository;
import com.notificationserver.adaptor.out.infrastructure.mysql.repository.NotificationLogJpaRepository;
import com.notificationserver.application.port.out.LoadNotificationPort;
import com.notificationserver.application.port.out.SaveNotificationPort;
import com.notificationserver.application.port.out.dto.LoadNotificationLogOutDto;
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
		// 이미 등록된 FCM 토큰인지 확인
		if (notificationJpaRepository.existsByUuidAndFcmToken(
				saveNotificationOutDto.getUuid(), saveNotificationOutDto.getFcmToken())) {
			throw new CustomException(BaseResponseCode.ALREADY_EXIST_FCM_TOKEN);
		}

		notificationJpaRepository.save(NotificationEntity.toEntityFrom(saveNotificationOutDto));
	}

	@Override
	@Transactional
	public void deleteByUuidAndFcmToken(String uuid, String fcmToken) {
		notificationJpaRepository.deleteNotificationByUuidAndFcmToken(uuid, fcmToken);
	}

	@Override
	@Transactional
	public void saveNotificationLog(SaveNotificationLogOutDto saveNotificationLogOutDto) {
		notificationLogJpaRepository.save(
				NotificationLogEntity.toEntityFrom(saveNotificationLogOutDto));
	}

	@Override
	@Transactional
	public void updateNotificationLogReadStatus(ReadNotificationLogOutDto dto) {
		NotificationLogEntity notificationLogEntity = notificationLogJpaRepository.findByUuidAndId(
						dto.getUuid(), dto.getNotificationId())
				.orElseThrow(() -> new CustomException(
						BaseResponseCode.NOT_UPDATE_NOTIFICATION_LOG_READ_STATUS));

		notificationLogJpaRepository.save(
				NotificationLogEntity.updateReadStatus(notificationLogEntity, dto.getReadStatus()));
	}

	@Override
	@Transactional
	public void deleteNotificationLogsByIdsAndUuid(List<Long> notificationLogIds, String uuid) {
		notificationLogJpaRepository.deleteNotificationLogsByIdsAndUuid(notificationLogIds, uuid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getFcmTokenByUuid(String uuid) {
		return notificationJpaRepository.findFcmTokensByUuid(uuid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LoadNotificationLogOutDto> getNotificationLogByUuid(String uuid) {
		return notificationLogJpaRepository.findByUuid(uuid)
				.stream()
				.map(notificationLogEntity -> LoadNotificationLogOutDto.builder()
						.notificationLogId(notificationLogEntity.getId())
						.content(notificationLogEntity.getContent())
						.title(notificationLogEntity.getTitle())
						.notificationStatus(notificationLogEntity.getNotificationStatus())
						.readStatus(notificationLogEntity.getReadStatus())
						.notificationLogCreateAt(notificationLogEntity.getNotificationLogCreateAt())
						.build())
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public long getCountByUuidAndReadStatus(String uuid, Integer readStatus) {
		return notificationLogJpaRepository.getCountByUuidAndReadStatus(uuid, readStatus);
	}
}
