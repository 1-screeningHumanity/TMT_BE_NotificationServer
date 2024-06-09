package com.notificationserver.domain;

import com.notificationserver.application.port.in.dto.SaveNotificationLogInDto;
import com.notificationserver.domain.enums.NotificationStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Notification {

	private String fcmToken;
	private String uuid;
	private LocalDateTime notificationCreateAt;
	private String content;
	private String title;
	private NotificationStatus notificationStatus;
	private Integer readStatus;
	private LocalDateTime notificationLogCreateAt;

	public static Notification sendAlarm(String fcmToken, SaveNotificationLogInDto dto) {
		return Notification.builder()
				.fcmToken(fcmToken)
				.content(dto.getContent())
				.title(dto.getTitle())
				.readStatus(dto.getReadStatus())
				.notificationStatus(dto.getNotificationStatus())
				.notificationLogCreateAt(dto.getNotificationLogCreateAt())
				.build();
	}
}
