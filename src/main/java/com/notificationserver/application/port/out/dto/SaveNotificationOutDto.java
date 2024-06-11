package com.notificationserver.application.port.out.dto;

import com.notificationserver.application.port.in.dto.SaveNotificationInDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveNotificationOutDto {
	private String fcmToken;
	private String uuid;

	public static SaveNotificationOutDto getSaveNotificationInDto(SaveNotificationInDto dto) {
		return SaveNotificationOutDto.builder()
			.fcmToken(dto.getFcmToken())
			.uuid(dto.getUuid())
			.build();
	}
}
