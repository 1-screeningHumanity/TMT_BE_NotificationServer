package com.notificationserver.application.port.out.dto;

import com.notificationserver.domain.Notification;
import com.notificationserver.domain.enums.NotificationStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SaveNotificationLogOutDto {
	private String uuid;
	private String title;
	private String content;
	private NotificationStatus notificationStatus;
	private Integer readStatus;
	private LocalDateTime notificationLogCreateAt;

	public static SaveNotificationLogOutDto getNotification(
			Notification notification) {
		return SaveNotificationLogOutDto.builder()
				.uuid(notification.getUuid())
				.title(notification.getTitle())
				.content(notification.getContent())
				.notificationStatus(notification.getNotificationStatus())
				.readStatus(notification.getReadStatus())
				.notificationLogCreateAt(notification.getNotificationLogCreateAt())
				.build();
	}
}
