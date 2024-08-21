package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkLogRepository extends JpaRepository<WalkLogEntity, Long>,
    CustomWalkLogRepository {

    default WalkLogEntity getById(long walkLogId) {
        return findById(walkLogId)
            .orElseThrow(
                () -> new IllegalArgumentException("해당 산책 기록이 존재하지 않습니다. walkLogId: " + walkLogId));
    }

}
