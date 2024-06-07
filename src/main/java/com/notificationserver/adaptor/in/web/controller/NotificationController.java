package com.notificationserver.adaptor.in.web.controller;

import com.notificationserver.adaptor.in.web.vo.FcmTokenVo;
import com.notificationserver.application.port.in.dto.SaveNotificationInDto;
import com.notificationserver.application.port.in.usecase.NotificationUseCase;
import com.notificationserver.global.common.response.BaseResponse;
import com.notificationserver.global.common.token.DecodingToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
	private final NotificationUseCase notificationUseCase;
	private final DecodingToken decodingToken;

	@PostMapping("/notification/fcm-token")
	public BaseResponse<Void> saveFcmTokenByUuid(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
			@RequestBody FcmTokenVo vo) {

		String uuid = decodingToken.getUuid(accessToken);

		notificationUseCase.saveFcmTokenByUuid(SaveNotificationInDto.of(vo.getFcmToken(), uuid));

		return new BaseResponse<>();
	}
}
