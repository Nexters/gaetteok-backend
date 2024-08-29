package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.FriendWalkStatus;

import java.time.LocalDate;
import java.util.List;

public interface CustomFriendRepository {

    List<FriendWalkStatus> getFriendWalkStatus(long userId, LocalDate date);

    long deleteByUserId(long userId);

    long restoreByUserId(long userId);

}
