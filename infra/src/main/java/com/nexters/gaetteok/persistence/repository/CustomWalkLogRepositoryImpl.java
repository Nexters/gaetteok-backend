package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nexters.gaetteok.persistence.entity.QWalkLogEntity.walkLogEntity;

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

}
