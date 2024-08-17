package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.FriendWalkStatus;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.nexters.gaetteok.persistence.entity.QFriendEntity.friendEntity;
import static com.nexters.gaetteok.persistence.entity.QUserEntity.userEntity;
import static com.nexters.gaetteok.persistence.entity.QWalkLogEntity.walkLogEntity;
import static com.querydsl.core.types.dsl.Expressions.dateTimeOperation;

@Repository
@RequiredArgsConstructor
public class CustomFriendRepositoryImpl implements CustomFriendRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FriendWalkStatus> getFriendWalkStatus(long userId, LocalDate date) {
        return jpaQueryFactory
            .select(Projections.constructor(
                FriendWalkStatus.class,
                userEntity.id,
                userEntity.nickname,
                userEntity.profileUrl,
                walkLogEntity.count().gt(0).as("done"),
                friendEntity.createdAt.as("friendCreatedAt"),
                // 마지막 걸음 날짜가 없으면 가장 마지막 시간으로 설정
                walkLogEntity.createdAt.max()
                    .coalesce(date.atTime(23, 59, 59, 999))
                    .as("lastWalkDate")
            ))
            .from(friendEntity)
            .leftJoin(userEntity).on(friendEntity.friendUserId.eq(userEntity.id))
            .leftJoin(walkLogEntity).on(
                userEntity.id.eq(walkLogEntity.userId),
                dateTimeOperation(LocalDate.class, Ops.DateTimeOps.DATE, walkLogEntity.createdAt).eq(date)
            )
            .groupBy(userEntity.id, userEntity.nickname, userEntity.profileUrl, friendEntity.createdAt)
            .where(friendEntity.myUserId.eq(userId))
            .fetch();
    }

}
