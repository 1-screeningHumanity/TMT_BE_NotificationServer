package com.notificationserver.adaptor.in.web.controller;

import com.notificationserver.adaptor.in.web.vo.FcmTokenVo;
import com.notificationserver.adaptor.in.web.vo.LoadNotificationLogVo;
import com.notificationserver.adaptor.in.web.vo.NotificationIdsVo;
import com.notificationserver.adaptor.in.web.vo.NotificationLogCountVo;
import com.notificationserver.application.port.in.dto.SaveNotificationInDto;
import com.notificationserver.application.port.in.usecase.NotificationUseCase;
import com.notificationserver.global.common.response.BaseResponse;
import com.notificationserver.global.common.token.DecodingToken;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
// 주의: Gateway 로드 밸런서 설정으로 인해 @RequestMapping("/notification")이 생략되었습니다.
// 로드 밸런서가 이 엔드포인트로의 라우팅을 처리합니다.
public class NotificationController {

	private final NotificationUseCase notificationUseCase;
	private final DecodingToken decodingToken;

	@PostMapping("/fcm-token")
	public BaseResponse<Void> saveFcmTokenByUuid(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
			@RequestBody FcmTokenVo vo) {

		String uuid = decodingToken.getUuid(accessToken);

		notificationUseCase.saveFcmTokenByUuid(SaveNotificationInDto.of(vo.getFcmToken(), uuid));

		return new BaseResponse<>();
	}

	@PostMapping
	public BaseResponse<Void> readAlarm(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
			@RequestBody NotificationIdsVo vo) {

		String uuid = decodingToken.getUuid(accessToken);

		notificationUseCase.readAlarm(vo.getNotificationIds(), uuid);

		return new BaseResponse<>();
	}

	@GetMapping
	public BaseResponse<List<LoadNotificationLogVo>> getAlarm(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {

//		String uuid = decodingToken.getUuid(accessToken);

		String uuid = "abcd";

		return new BaseResponse<>(notificationUseCase.getAlarm(uuid)
				.stream()
				.map(LoadNotificationLogVo::getLoadNotificationLogInDto)
				.toList());
	}

	@DeleteMapping
	public BaseResponse<Void> deleteAlarms(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
			@RequestBody NotificationIdsVo vo) {

		String uuid = decodingToken.getUuid(accessToken);

		notificationUseCase.deleteAlarms(vo.getNotificationIds(), uuid);

		return new BaseResponse<>();
	}

	@GetMapping("/count")
	public BaseResponse<NotificationLogCountVo> getAlarmCount(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {

		String uuid = decodingToken.getUuid(accessToken);

		return new BaseResponse<>(NotificationLogCountVo.getNotificationLogInDto(
				notificationUseCase.getAlarmCount(uuid)));
	}

	@DeleteMapping("/fcm-token")
	public BaseResponse<Void> deleteFcmToken(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
			@RequestBody FcmTokenVo vo
	) {
//		String uuid = decodingToken.getUuid(accessToken);

		String uuid = "abcd";
		notificationUseCase.deleteFcmToken(uuid, vo.getFcmToken());
		return new BaseResponse<>();
	}
}
