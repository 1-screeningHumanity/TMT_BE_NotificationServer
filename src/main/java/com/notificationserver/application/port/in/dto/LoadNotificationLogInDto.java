package com.notificationserver.application.port.in.dto;

import com.notificationserver.domain.Notification;
import com.notificationserver.domain.enums.NotificationStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoadNotificationLogInDto {
	private Long notificationLogId;
	private String content;
	private String title;
	private NotificationStatus notificationStatus;
	private Integer readStatus;
	private LocalDateTime notificationLogCreateAt;

	@Builder
	public static LoadNotificationLogInDto getNotification(Notification notification) {
		return LoadNotificationLogInDto.builder()
				.notificationLogId(notification.getNotificationLogId())
				.content(notification.getContent())
				.title(notification.getTitle())
				.notificationStatus(notification.getNotificationStatus())
				.readStatus(notification.getReadStatus())
				.notificationLogCreateAt(notification.getNotificationLogCreateAt())
				.build();
	}
}
