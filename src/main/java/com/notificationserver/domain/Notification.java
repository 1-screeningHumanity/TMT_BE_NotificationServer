package com.notificationserver.domain;

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
	// todo enum 관리
	private String notificationStatus;
	private Integer readStatus;
	private LocalDateTime notificationLogCreateAt;

	public static void sendAlarm() {
		// todo 알림 전송

	}
}
