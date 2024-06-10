package com.notificationserver.application.port.out.dto;

import com.notificationserver.domain.Notification;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadNotificationLogOutDto {
	private Long notificationId;
	private Integer readStatus;

	@Builder
	public static ReadNotificationLogOutDto getNoticaiton(Notification notification) {
		return ReadNotificationLogOutDto.builder()
				.notificationId(notification.getNotificationLogId())
				.readStatus(notification.getReadStatus())
				.build();
	}

}
