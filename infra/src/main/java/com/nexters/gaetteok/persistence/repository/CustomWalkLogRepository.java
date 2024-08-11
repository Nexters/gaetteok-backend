package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.WalkLogEntity;

import java.util.List;

public interface CustomWalkLogRepository {

    List<WalkLogEntity> getMyList(long userId, long cursorId, int pageSize);

}
