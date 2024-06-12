package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import static com.notificationserver.adaptor.out.infrastructure.mysql.entity.QNotificationEntity.notificationEntity;
import static com.notificationserver.adaptor.out.infrastructure.mysql.entity.QNotificationLogEntity.notificationLogEntity;


import com.notificationserver.adaptor.out.infrastructure.mysql.entity.NotificationLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationLogQueryDslRepositoryImpl implements NotificationLogQueryDslRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<NotificationLogEntity> findNotificationLogByUuidAndId(String uuid, Long notificationLogId) {
		return Optional.ofNullable( jpaQueryFactory.selectFrom(notificationLogEntity)
				.leftJoin(notificationEntity)
				.on(notificationEntity.id.eq(notificationLogEntity.notification.id))
				.fetchJoin()
				.where(notificationEntity.uuid.eq(uuid),
						notificationLogEntity.id.eq(notificationLogId))
				.fetchOne());
	}

	@Override
	public List<NotificationLogEntity> findNotificationLogByUuid(String uuid) {
		return jpaQueryFactory.selectFrom(notificationLogEntity)
				.leftJoin(notificationEntity)
				.on(notificationEntity.id.eq(notificationLogEntity.notification.id))
				.fetchJoin()
				.where(notificationEntity.uuid.eq(uuid))
				.fetch();
	}

	@Override
	public void deleteNotificationLogsByIdsAndUuid(List<Long> notificationLogIds, String uuid) {
		jpaQueryFactory.delete(notificationLogEntity)
				.where(notificationLogEntity.notification.uuid.eq(uuid)
						.and(notificationLogEntity.id.in(notificationLogIds)))
				.execute();
	}


}
