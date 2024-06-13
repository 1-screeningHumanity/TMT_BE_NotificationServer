package com.notificationserver.adaptor.out.infrastructure.mysql.repository;

import static com.notificationserver.adaptor.out.infrastructure.mysql.entity.QNotificationLogEntity.notificationLogEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationLogQueryDslRepositoryImpl implements NotificationLogQueryDslRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public void deleteNotificationLogsByIdsAndUuid(List<Long> notificationLogIds, String uuid) {
		jpaQueryFactory.delete(notificationLogEntity)
				.where(notificationLogEntity.uuid.eq(uuid)
						.and(notificationLogEntity.id.in(notificationLogIds)))
				.execute();
	}

	@Override
	public long getCountByUuidAndReadStatus(String uuid, Integer readStatus) {
		 return jpaQueryFactory.select(notificationLogEntity.count())
				.from(notificationLogEntity)
				.where(notificationLogEntity.uuid.eq(uuid)
						.and(notificationLogEntity.readStatus.eq(readStatus)))
				.fetchFirst();
	}


}
