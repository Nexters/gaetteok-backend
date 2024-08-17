package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<FriendEntity, Long>, CustomFriendRepository {

    List<FriendEntity> findByMyUserId(long id);

    Optional<FriendEntity> findByMyUserIdAndFriendUserId(long myUserId, long friendUserId);

    default FriendEntity getByMyUserIdAndFriendUserId(long myUserId, long friendUserId) {
        return findByMyUserIdAndFriendUserId(myUserId, friendUserId)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자와 친구 관계가 존재하지 않습니다. myUserId: " + myUserId + ", friendUserId: " + friendUserId));
    }

}
