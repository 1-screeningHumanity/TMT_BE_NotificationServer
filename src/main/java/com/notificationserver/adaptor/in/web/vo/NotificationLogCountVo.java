package com.notificationserver.adaptor.in.web.vo;

import com.notificationserver.application.port.in.dto.NotificationLogCountInDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationLogCountVo {

	private Integer notificationLogCount;

	public static NotificationLogCountVo getNotificationLogInDto(NotificationLogCountInDto dto) {
		return NotificationLogCountVo.builder()
				.notificationLogCount(dto.getNotificationLogCount())
				.build();
	}
}
