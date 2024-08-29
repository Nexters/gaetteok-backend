package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.Comment;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nexters.gaetteok.persistence.entity.QCommentEntity.commentEntity;
import static com.nexters.gaetteok.persistence.entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findByWalkLogIdInWithUser(long walkLogId) {
        return queryFactory.select(Projections.constructor(Comment.class,
                commentEntity.id,
                commentEntity.content,
                userEntity.id,
                userEntity.nickname,
                userEntity.profileUrl,
                commentEntity.walkLogId,
                commentEntity.createdAt
            ))
            .from(commentEntity)
            .join(userEntity).on(commentEntity.userId.eq(userEntity.id))
            .where(commentEntity.walkLogId.eq(walkLogId))
            .orderBy(commentEntity.id.asc())
            .fetch();
    }

    @Override
    public long deleteByUserId(long userId) {
        return queryFactory
            .update(commentEntity)
            .set(commentEntity.deleted, true)
            .where(commentEntity.userId.eq(userId))
            .execute();
    }

    @Override
    public long restoreByUserId(long userId) {
        return queryFactory
            .update(commentEntity)
            .set(commentEntity.deleted, false)
            .where(commentEntity.userId.eq(userId))
            .execute();
    }

}
