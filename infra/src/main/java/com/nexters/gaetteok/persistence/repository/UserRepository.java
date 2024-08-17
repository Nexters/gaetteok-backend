package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCode(String code);

    Optional<UserEntity> findByOauthIdentifier(String oauthIdentifier);

    default UserEntity getById(long userId) {
        return findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. userId: " + userId));
    }

}
