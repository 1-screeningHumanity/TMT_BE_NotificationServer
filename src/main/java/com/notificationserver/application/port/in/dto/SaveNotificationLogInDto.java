package com.notificationserver.application.port.in.dto;

import com.notificationserver.domain.enums.NotificationStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SaveNotificationLogInDto {
	private String uuid;
	private String title;
	private String content;
	private NotificationStatus notificationStatus;
	private Integer readStatus;
	private LocalDateTime notificationLogCreateAt;

	@Builder
	public static SaveNotificationLogInDto createNotificationLogInDto(String uuid, String title, String content, LocalDateTime notificationLogCreateAt, NotificationStatus notificationStatus){
		return SaveNotificationLogInDto.builder()
				.uuid(uuid)
				.title(title)
				.content(content)
				.notificationStatus(notificationStatus)
				.readStatus(44)
				.notificationLogCreateAt(notificationLogCreateAt)
				.build();
	}
}
