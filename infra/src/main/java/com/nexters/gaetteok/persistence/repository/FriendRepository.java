package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.FriendEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    List<FriendEntity> findByMe(UserEntity me);

}
