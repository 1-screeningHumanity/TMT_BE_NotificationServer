package com.notificationserver.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationLogCountInDto {
	private Integer notificationLogCount;

	public static NotificationLogCountInDto getCount(Integer count) {
		return NotificationLogCountInDto.builder()
				.notificationLogCount(count)
				.build();
	}
}
