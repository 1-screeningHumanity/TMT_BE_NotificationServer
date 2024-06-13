package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import static com.notificationserver.adaptor.out.infrastructure.mysql.entity.QNotificationEntity.notificationEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationQueryDslRepositoryImpl implements NotificationQueryDslRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<String> findFcmTokensByUuid(String uuid) {
		return jpaQueryFactory
				.select(notificationEntity.fcmToken)
				.from(notificationEntity)
				.where(notificationEntity.uuid.eq(uuid))
				.fetch();
	}

	@Override
	public void deleteNotificationByUuidAndFcmToken(String uuid, String fcmToken) {
		jpaQueryFactory.delete(notificationEntity)
				.where(notificationEntity.uuid.eq(uuid)
						.and(notificationEntity.fcmToken.eq(fcmToken)))
				.execute();
	}
}
