package com.notificationserver.adaptor.out.infrastructure.mysql.entity;

import com.notificationserver.application.port.out.dto.SaveNotificationLogOutDto;
import com.notificationserver.domain.enums.NotificationStatus;
import com.notificationserver.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_entity_id")
	private NotificationEntity notification;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "notification_status", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private NotificationStatus notificationStatus;

	@Column(name = "read_status", nullable = false)
	private Integer readStatus;

	@Column(name = "notification_log_create_at", nullable = false)
	private LocalDateTime notificationLogCreateAt;

	public static NotificationLogEntity toEntityFrom(
			NotificationEntity notificationEntity,SaveNotificationLogOutDto saveNotificationLogOutDto) {
		return NotificationLogEntity.builder()
				.notification(notificationEntity)
				.title(saveNotificationLogOutDto.getTitle())
				.content(saveNotificationLogOutDto.getContent())
				.notificationStatus(saveNotificationLogOutDto.getNotificationStatus())
				.readStatus(saveNotificationLogOutDto.getReadStatus())
				.notificationLogCreateAt(saveNotificationLogOutDto.getNotificationLogCreateAt())
				.build();
	}
}
