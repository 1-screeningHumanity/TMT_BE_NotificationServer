package com.notificationserver.application.port.out.dto;

import com.notificationserver.domain.Notification;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadNotificationLogOutDto {
	private Long notificationId;
	private Integer readStatus;
	private String uuid;

	@Builder
	public static ReadNotificationLogOutDto getNotification(Notification notification) {
		return ReadNotificationLogOutDto.builder()
				.notificationId(notification.getNotificationLogId())
				.readStatus(notification.getReadStatus())
				.uuid(notification.getUuid())
				.build();
	}

}
