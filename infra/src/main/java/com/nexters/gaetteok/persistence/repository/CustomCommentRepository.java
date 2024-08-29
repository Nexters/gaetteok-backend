package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.Comment;

import java.util.List;

public interface CustomCommentRepository {

    List<Comment> findByWalkLogIdInWithUser(long walkLogId);

    long deleteByUserId(long userId);

    long restoreByUserId(long userId);

}
