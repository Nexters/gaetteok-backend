package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
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
public class CustomWalkLogRepositoryImpl implements CustomWalkLogRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<WalkLogEntity> getMyList(long userId, long cursorId, int pageSize) {
        return jpaQueryFactory.selectFrom(walkLogEntity)
                .where(walkLogEntity.userId.eq(userId), cursorId > 0 ? walkLogEntity.id.lt(cursorId) : null)
                .orderBy(walkLogEntity.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<WalkLog> getList(long userId, long cursorId, int pageSize, LocalDate date) {
        List<Long> friendIdList = jpaQueryFactory
                .select(friendEntity.friendUserId)
                .from(friendEntity)
                .where(friendEntity.myUserId.eq(userId))
                .fetch();
        // 피드에는 나도 포함
        friendIdList.add(userId);

        return jpaQueryFactory.select(Projections.constructor(
                        WalkLog.class,
                        walkLogEntity.id,
                        walkLogEntity.photoUrl,
                        walkLogEntity.title,
                        walkLogEntity.content,
                        walkLogEntity.walkTime,
                        userEntity.nickname,
                        userEntity.profileUrl,
                        walkLogEntity.createdAt
                ))
                .from(walkLogEntity)
                .join(userEntity)
                .on(userEntity.id.eq(walkLogEntity.userId))
                .where(
                        cursorId > 0 ? walkLogEntity.id.lt(cursorId) : null,
                        dateTimeOperation(LocalDate.class, Ops.DateTimeOps.DATE, walkLogEntity.createdAt).eq(date),
                        userEntity.id.in(friendIdList)
                )
                .limit(pageSize)
                .orderBy(walkLogEntity.id.desc())
                .fetch();
    }

}
