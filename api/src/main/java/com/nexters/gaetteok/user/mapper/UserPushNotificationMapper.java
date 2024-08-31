package com.nexters.gaetteok.user.mapper;

import com.nexters.gaetteok.domain.UserPushNotification;
import com.nexters.gaetteok.persistence.entity.UserPushNotificationEntity;

public class UserPushNotificationMapper {

    public static UserPushNotificationEntity toEntity(
        UserPushNotification userPushNotificationDomain) {
        return new UserPushNotificationEntity(
            userPushNotificationDomain.getId(),
            userPushNotificationDomain.getUserId(),
            userPushNotificationDomain.getPushNotificationTime(),
            userPushNotificationDomain.isOn()
        );
    }

    public static UserPushNotification toDomain(
        UserPushNotificationEntity userPushNotificationEntity) {
        return new UserPushNotification(
            userPushNotificationEntity.getId(),
            userPushNotificationEntity.getUserId(),
            userPushNotificationEntity.getPushNotificationTime(),
            userPushNotificationEntity.isOn()
        );
    }

}
