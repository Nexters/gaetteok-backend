package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCode(String code);

}
