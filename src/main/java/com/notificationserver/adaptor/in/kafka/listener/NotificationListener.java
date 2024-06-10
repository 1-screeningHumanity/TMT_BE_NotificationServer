package com.notificationserver.adaptor.in.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificationserver.adaptor.in.kafka.vo.NotificationInfoVo;
import com.notificationserver.application.port.in.dto.SaveNotificationLogInDto;
import com.notificationserver.application.port.in.usecase.NotificationUseCase;
import com.notificationserver.domain.enums.NotificationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationListener {

	private final NotificationUseCase notificationUseCase;

	private final ObjectMapper mapper;

	@KafkaListener(topics = "trade-notification-alarm")
	public void getTradeAlarm(String kafkaMessage) {
		try {
			NotificationInfoVo vo = mapper.readValue(kafkaMessage, new TypeReference<>() {
			});

			notificationUseCase.sendAlarm(
					vo.getUuid(),
					SaveNotificationLogInDto.createNotificationLogInDto(vo.getTitle(), vo.getBody(),
							NotificationStatus.TRADE));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}