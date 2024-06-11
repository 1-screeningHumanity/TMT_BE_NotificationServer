package com.notificationserver.adaptor.in.web.vo;

import com.notificationserver.application.port.in.dto.LoadNotificationLogInDto;
import com.notificationserver.domain.enums.NotificationStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoadNotificationLogVo {

	private Long notificationLogId;
	private String content;
	private String title;
	private NotificationStatus notificationStatus;
	private Integer readStatus;
	private LocalDateTime notificationLogCreateAt;

	@Builder
	public static LoadNotificationLogVo getLoadNotificationLogInDto(LoadNotificationLogInDto dto) {
		return LoadNotificationLogVo.builder()
				.notificationLogId(dto.getNotificationLogId())
				.content(dto.getContent())
				.title(dto.getTitle())
				.notificationStatus(dto.getNotificationStatus())
				.readStatus(dto.getReadStatus())
				.notificationLogCreateAt(dto.getNotificationLogCreateAt())
				.build();
	}
}