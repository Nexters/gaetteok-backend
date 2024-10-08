package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<WalkLogEntity> getCalendar(long userId, int year, int month) {
        return jpaQueryFactory.selectFrom(walkLogEntity)
            .where(
                walkLogEntity.userId.eq(userId),
                walkLogEntity.createdAt.after(LocalDateTime.of(year, month, 1, 0, 0)),
                walkLogEntity.createdAt.before(LocalDateTime.of(year, month + 1, 1, 0, 0)),
                walkLogEntity.deleted.isFalse()
            )
            .fetch();
    }

    @Override
    public List<WalkLog> getListOfMeAndMyFriend(long userId, long cursorId, int pageSize) {
        List<Long> friendIdList = jpaQueryFactory
            .select(friendEntity.friendUserId)
            .from(friendEntity)
            .where(friendEntity.myUserId.eq(userId), friendEntity.deleted.isFalse())
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
                userEntity.id,
                userEntity.nickname,
                userEntity.profileUrl,
                walkLogEntity.createdAt
            ))
            .from(walkLogEntity)
            .join(userEntity)
            .on(userEntity.id.eq(walkLogEntity.userId))
            .where(
                cursorId > 0 ? walkLogEntity.id.lt(cursorId) : null,
                userEntity.id.in(friendIdList),
                walkLogEntity.deleted.isFalse()
            )
            .limit(pageSize)
            .orderBy(walkLogEntity.id.desc())
            .fetch();
    }

    @Override
    public List<WalkLog> getListOnlyMe(long userId, long cursorId, int pageSize) {
        return jpaQueryFactory.select(Projections.constructor(
                WalkLog.class,
                walkLogEntity.id,
                walkLogEntity.photoUrl,
                walkLogEntity.title,
                walkLogEntity.content,
                walkLogEntity.walkTime,
                userEntity.id,
                userEntity.nickname,
                userEntity.profileUrl,
                walkLogEntity.createdAt
            ))
            .from(walkLogEntity)
            .join(userEntity)
            .on(userEntity.id.eq(walkLogEntity.userId))
            .where(
                cursorId > 0 ? walkLogEntity.id.lt(cursorId) : null,
                userEntity.id.eq(userId),
                walkLogEntity.deleted.isFalse()
            )
            .limit(pageSize)
            .orderBy(walkLogEntity.id.desc())
            .fetch();
    }

    @Override
    public List<WalkLogEntity> getListByUserIdAndMonth(long userId, int year, int month) {
        return jpaQueryFactory.selectFrom(walkLogEntity)
            .where(
                walkLogEntity.userId.eq(userId),
                walkLogEntity.createdAt.year().eq(year),
                walkLogEntity.createdAt.month().eq(month),
                walkLogEntity.deleted.isFalse()
            )
            .orderBy(walkLogEntity.id.desc())
            .fetch();
    }

    @Override
    public WalkLogEntity getMaxIdLessThan(long walkLogId, long userId) {
        return jpaQueryFactory.selectFrom(walkLogEntity)
            .where(walkLogEntity.id.lt(walkLogId), walkLogEntity.userId.eq(userId), walkLogEntity.deleted.isFalse())
            .orderBy(walkLogEntity.id.desc())
            .fetchFirst();
    }

    @Override
    public WalkLogEntity getMaxIdLessThan(int year, int month, long userId) {
        int nextYear = month > 1 ? year : year - 1;
        int nextMonth = month > 1 ? month - 1 : 12;
        return jpaQueryFactory.selectFrom(walkLogEntity)
            .where(
                walkLogEntity.createdAt.year().loe(nextYear),
                walkLogEntity.createdAt.month().loe(nextMonth),
                walkLogEntity.userId.eq(userId),
                walkLogEntity.deleted.isFalse()
            )
            .orderBy(walkLogEntity.id.desc())
            .fetchFirst();
    }

    @Override
    public boolean isTodayWalkLogExists(long userId, LocalDate date) {
        Long id = jpaQueryFactory
            .select(walkLogEntity.id)
            .from(walkLogEntity)
            .where(
                walkLogEntity.userId.eq(userId),
                dateTimeOperation(LocalDate.class, Ops.DateTimeOps.DATE, walkLogEntity.createdAt).eq(date),
                walkLogEntity.deleted.isFalse()
            )
            .fetchFirst();

        return id != null;
    }

    @Override
    public long deleteByUserId(long userId) {
        return jpaQueryFactory
            .update(walkLogEntity)
            .set(walkLogEntity.deleted, true)
            .where(walkLogEntity.userId.eq(userId))
            .execute();
    }

    @Override
    public long restoreByUserId(long userId) {
        return jpaQueryFactory
            .update(walkLogEntity)
            .set(walkLogEntity.deleted, false)
            .where(walkLogEntity.userId.eq(userId))
            .execute();
    }

    @Override
    public WalkLogEntity findRecentWalkLogByUserIdAndDate(long userId, LocalDate date) {
        return jpaQueryFactory
            .selectFrom(walkLogEntity)
            .where(
                walkLogEntity.userId.eq(userId),
                dateTimeOperation(LocalDate.class, Ops.DateTimeOps.DATE, walkLogEntity.createdAt).eq(date),
                walkLogEntity.deleted.isFalse()
            )
            .orderBy(walkLogEntity.createdAt.desc())
            .fetchFirst();
    }

}
