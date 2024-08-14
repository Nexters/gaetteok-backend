package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkLogRepository extends JpaRepository<WalkLogEntity, Long>, CustomWalkLogRepository {

}
