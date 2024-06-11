package com.notificationserver.application.port.out.dto;

import com.notificationserver.domain.enums.NotificationStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoadNotificationLogOutDto {
	private Long notificationLogId;
	private String content;
	private String title;
	private NotificationStatus notificationStatus;
	private Integer readStatus;
	private LocalDateTime notificationLogCreateAt;

}
