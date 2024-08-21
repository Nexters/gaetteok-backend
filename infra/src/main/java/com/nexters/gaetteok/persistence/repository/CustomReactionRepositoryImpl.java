package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.Reaction;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nexters.gaetteok.persistence.entity.QReactionEntity.reactionEntity;
import static com.nexters.gaetteok.persistence.entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class CustomReactionRepositoryImpl implements CustomReactionRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Reaction> findByWalkLogIdInWithUser(long walkLogId) {
        return queryFactory.select(Projections.constructor(Reaction.class,
                reactionEntity.id,
                userEntity.id,
                userEntity.nickname,
                userEntity.profileUrl,
                reactionEntity.reactionType,
                reactionEntity.walkLogId,
                reactionEntity.createdAt
            ))
            .from(reactionEntity)
            .join(userEntity).on(reactionEntity.userId.eq(userEntity.id))
            .where(reactionEntity.walkLogId.eq(walkLogId))
            .orderBy(reactionEntity.id.asc())
            .fetch();
    }

}
