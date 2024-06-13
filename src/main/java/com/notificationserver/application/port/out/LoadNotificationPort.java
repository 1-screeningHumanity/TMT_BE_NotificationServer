package com.notificationserver.application.port.out;

import com.notificationserver.application.port.out.dto.LoadNotificationLogOutDto;
import java.util.List;

public interface LoadNotificationPort {

	List<String> getFcmTokenByUuid(String uuid);

	List<LoadNotificationLogOutDto> getNotificationLogByUuid(String uuid);

	long getCountByUuidAndReadStatus(String uuid, Integer readStatus);
}
