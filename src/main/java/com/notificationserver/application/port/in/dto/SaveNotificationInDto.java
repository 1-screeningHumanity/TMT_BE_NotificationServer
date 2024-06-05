package com.notificationserver.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveNotificationInDto {
	private String fcmToken;
	private String uuid;

	public static SaveNotificationInDto of(String fcmToken, String uuid) {
		return SaveNotificationInDto.builder()
			.fcmToken(fcmToken)
			.uuid(uuid)
			.build();
	}
}
