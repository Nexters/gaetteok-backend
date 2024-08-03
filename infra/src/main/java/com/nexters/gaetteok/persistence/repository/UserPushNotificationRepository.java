package com.nexters.gaetteok.persistence.repository;

import com.nexters.gaetteok.persistence.entity.UserPushNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPushNotificationRepository extends
    JpaRepository<UserPushNotificationEntity, Long> {

    UserPushNotificationEntity findByUserId(long id);
}
