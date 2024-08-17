package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPushNotification {

    private long id;

    private long userId;

    private int pushNotificationTime;

    public void setPushNotificationTime(int pushNotificationTime) {
        this.pushNotificationTime = pushNotificationTime;
    }

    @Builder
    public UserPushNotification(long id, long userId, int pushNotificationTime) {
        this.id = id;
        this.userId = userId;
        this.pushNotificationTime = pushNotificationTime;
    }

}
