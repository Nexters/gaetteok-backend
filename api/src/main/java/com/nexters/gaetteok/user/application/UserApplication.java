package com.nexters.gaetteok.user.application;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.domain.UserPushNotification;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.UserPushNotificationEntity;
import com.nexters.gaetteok.persistence.repository.UserPushNotificationRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplication {

    private final UserRepository userRepository;
    private final UserPushNotificationRepository userPushNotificationRepository;

    private UserPushNotificationEntity toPushNotificationEntity(
        UserPushNotification userPushNotificationDomain) {
        return new UserPushNotificationEntity(
            userPushNotificationDomain.getId(),
            userPushNotificationDomain.getUserId(),
            userPushNotificationDomain.getPushNotificationTime()
        );
    }

    private User toUserDomain(
        UserEntity userEntity,
        UserPushNotificationEntity userPushNotificationEntity
    ) {
        return new User(
            userEntity.getId(),
            null,
            null,
            null,
            LocalDateTime.now(),
            toUserPushNotificationDomain(userPushNotificationEntity)
        );
    }

    private UserPushNotification toUserPushNotificationDomain(
        UserPushNotificationEntity userPushNotificationEntity) {
        return new UserPushNotification(
            userPushNotificationEntity.getId(),
            userPushNotificationEntity.getUserId(),
            userPushNotificationEntity.getPushNotificationTime()
        );
    }

    public long getPushNotificationTime(long id) {
        UserPushNotificationEntity userPushNotification = userPushNotificationRepository.findByUserId(
            id);

        return userPushNotification.getPushNotificationTime();
    }

    public User getUser(long id) {
        return null;
    }

    public void updatePushNotificationTime(long id, long timeToBeUpdated) {
        UserPushNotificationEntity userPushNotification = userPushNotificationRepository.findByUserId(
            id);

        UserPushNotification userPushNotificationDomain = toUserPushNotificationDomain(
            userPushNotification);

        userPushNotificationDomain.setPushNotificationTime(timeToBeUpdated);

        UserPushNotificationEntity updatedEntity = toPushNotificationEntity(
            userPushNotificationDomain);

        userPushNotificationRepository.save(updatedEntity);
    }

}
