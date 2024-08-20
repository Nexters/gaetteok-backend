package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.ReactionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<ReactionEntity, Long> {

    default ReactionEntity getById(long reactionId) {
        return findById(reactionId)
            .orElseThrow(
                () -> new IllegalArgumentException("해당 리액션을 찾을 수 없습니다. reactionId=" + reactionId));
    }

    List<ReactionEntity> findByWalkLogIdIn(List<Long> walkLogIds);

    List<ReactionEntity> findByWalkLogId(Long walkLogId);

}
