package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.domain.Reaction;

import java.util.List;

public interface CustomReactionRepository {

    List<Reaction> findByWalkLogIdInWithUser(long walkLogId);

}
