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
        List<Long> friendIdList = jpaQueryFactory
                .select(friendEntity.friendUserId)
                .from(friendEntity)
                .where(friendEntity.myUserId.eq(userId))
                .fetch();

        return jpaQueryFactory
                .select(Projections.constructor(
                        FriendWalkStatus.class,
                        userEntity.nickname,
                        userEntity.profileUrl,
                        walkLogEntity.count().gt(0).as("done")
                ))
                .from(userEntity)
                .leftJoin(walkLogEntity).on(
                        userEntity.id.eq(walkLogEntity.userId),
                        dateTimeOperation(LocalDate.class, Ops.DateTimeOps.DATE, walkLogEntity.createdAt).eq(date)
                )
                .where(userEntity.id.in(friendIdList))
                .groupBy(userEntity.nickname, userEntity.profileUrl)
                .orderBy(walkLogEntity.createdAt.max().desc())
                .fetch();
    }

}
