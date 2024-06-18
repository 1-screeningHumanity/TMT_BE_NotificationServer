package com.notificationserver.application.port.out.dto;

import com.notificationserver.domain.Notification;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationSendOutDto {
	private String fcmToken;
	private String title;
	private String content;

	@Builder
	public NotificationSendOutDto(String fcmToken, String title, String content) {
		this.fcmToken = fcmToken;
		this.title = title;
		this.content = content;
	}

	public static NotificationSendOutDto getNotification(Notification notification) {
		return NotificationSendOutDto.builder()
				.fcmToken(notification.getFcmToken())
				.title(notification.getTitle())
				.content(notification.getContent())
				.build();
	}
}
