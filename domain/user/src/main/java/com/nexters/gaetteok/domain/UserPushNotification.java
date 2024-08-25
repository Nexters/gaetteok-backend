package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPushNotification {

    private long id;

    private long userId;

    private Integer pushNotificationTime;

    public void setPushNotificationTime(Integer pushNotificationTime) {
        this.pushNotificationTime = pushNotificationTime;
    }

    @Builder
    public UserPushNotification(long id, long userId, Integer pushNotificationTime) {
        this.id = id;
        this.userId = userId;
        this.pushNotificationTime = pushNotificationTime;
    }

}
