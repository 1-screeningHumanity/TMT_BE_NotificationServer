package com.notificationserver.application.port.out;

import com.notificationserver.application.port.out.dto.NotificationSendOutDto;

public interface NotificationSendPort {
	void sendNotification(NotificationSendOutDto notificationSendOutDto);
}
