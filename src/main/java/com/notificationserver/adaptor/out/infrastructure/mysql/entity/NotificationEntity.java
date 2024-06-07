package com.notificationserver.adaptor.out.infrastructure.mysql.entity;

import com.notificationserver.application.port.out.dto.SaveNotificationOutDto;
import com.notificationserver.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid", nullable = false)
	private String uuid;

	@Column(name = "fcm_token", nullable = false)
	private String fcmToken;

	public static NotificationEntity toEntityFrom(SaveNotificationOutDto saveNotificationOutDto) {
		return NotificationEntity.builder()
				.uuid(saveNotificationOutDto.getUuid())
				.fcmToken(saveNotificationOutDto.getFcmToken())
				.build();
	}
}
