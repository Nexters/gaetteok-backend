package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;

import java.util.List;

public interface CustomWalkLogRepository {

    List<WalkLogEntity> getCalendar(long userId, int year, int month);

    List<WalkLog> getListOfMeAndMyFriend(long userId, long cursorId, int pageSize);

    List<WalkLog> getListOnlyMe(long userId, long cursorId, int pageSize);

    List<WalkLogEntity> getListByUserIdAndMonth(long userId, int year, int month);

    WalkLogEntity getMaxIdLessThan(long walkLogId);

}
