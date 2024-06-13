package com.notificationserver.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationLogCountInDto {
	private long notificationLogCount;

	public static NotificationLogCountInDto getCount(long count) {
		return NotificationLogCountInDto.builder()
				.notificationLogCount(count)
				.build();
	}
}
