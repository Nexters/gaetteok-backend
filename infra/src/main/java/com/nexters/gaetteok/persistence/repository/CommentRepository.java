package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>, CustomCommentRepository {

    List<CommentEntity> findByWalkLogIdIn(List<Long> walkLogIds);

}
