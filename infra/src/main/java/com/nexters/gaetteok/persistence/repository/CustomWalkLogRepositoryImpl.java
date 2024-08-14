package com.nexters.gaetteok.persistence.repository;

import static com.nexters.gaetteok.persistence.entity.QFriendEntity.*;
import static com.nexters.gaetteok.persistence.entity.QUserEntity.*;
import static com.nexters.gaetteok.persistence.entity.QWalkLogEntity.*;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomWalkLogRepositoryImpl implements CustomWalkLogRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<WalkLogEntity> getCalendar(long userId, int year, int month) {
        return jpaQueryFactory.selectFrom(walkLogEntity)
            .where(
                walkLogEntity.userId.eq(userId),
                walkLogEntity.createdAt.after(LocalDateTime.of(year, month, 1, 0, 0)),
                walkLogEntity.createdAt.before(LocalDateTime.of(year, month + 1, 1, 0, 0))
            )
            .fetch();
    }

    @Override
    public List<WalkLog> getList(long userId, long cursorId, int pageSize) {
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
                userEntity.id.in(friendIdList)
            )
            .limit(pageSize)
            .orderBy(walkLogEntity.id.desc())
            .fetch();
    }

    @Override
    public List<WalkLogEntity> getMyList(long userId, long cursorId, int pageSize) {
        return jpaQueryFactory.selectFrom(walkLogEntity)
            .where(
                walkLogEntity.userId.eq(userId),
                cursorId > 0 ? walkLogEntity.id.lt(cursorId) : null
            )
            .orderBy(walkLogEntity.id.desc())
            .limit(pageSize)
            .fetch();
    }
}
