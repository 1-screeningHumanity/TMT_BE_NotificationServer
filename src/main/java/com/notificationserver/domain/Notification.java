package com.notificationserver.domain;

import com.notificationserver.application.port.in.dto.SaveNotificationLogInDto;
import com.notificationserver.application.port.out.dto.LoadNotificationLogOutDto;
import com.notificationserver.domain.enums.NotificationStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Notification {

	private Long notificationLogId;
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
				.uuid(dto.getUuid())
				.fcmToken(fcmToken)
				.content(dto.getContent())
				.title(dto.getTitle())
				.readStatus(dto.getReadStatus())
				.notificationStatus(dto.getNotificationStatus())
				.notificationLogCreateAt(dto.getNotificationLogCreateAt())
				.build();
	}

	public static Notification readAlarm(Long notificationLogId, String uuid) {
		return Notification.builder()
				.notificationLogId(notificationLogId)
				.readStatus(77)
				.uuid(uuid)
				.build();
	}

	public static List<Notification> getAlarm(List<LoadNotificationLogOutDto> dtos) {
		return dtos.stream()
				.map(dto ->
						Notification.builder()
								.notificationLogId(dto.getNotificationLogId())
								.title(dto.getTitle())
								.content(dto.getContent())
								.notificationStatus(dto.getNotificationStatus())
								.readStatus(dto.getReadStatus())
								.notificationLogCreateAt(dto.getNotificationLogCreateAt())
								.build())
				.toList();
	}

	public static List<Long> deleteAlarms(List<Long> notificationLogIds) {
		return notificationLogIds;
	}

}
