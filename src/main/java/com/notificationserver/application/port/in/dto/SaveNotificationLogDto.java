package com.notificationserver.application.port.in.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SaveNotificationLogDto {
	private String fcmToken;
	private String uuid;
	private String content;
	private String notificationStatus;
	private Integer readStatus;
	private LocalDateTime createAt;
}
