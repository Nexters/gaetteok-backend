package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    public List<CommentEntity> findByWalkLogIdIn(List<Long> walkLogIds);

}
