package com.notificationserver.application.port.out;

import java.util.List;

public interface LoadNotificationPort {

	List<String> getFcmTokenByUuid(String uuid);
}
