package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserPushNotification {

    private long id;

    private long userId;

    @Setter private Integer pushNotificationTime;

    @Setter private boolean isOn;

    @Builder
    public UserPushNotification(long id, long userId, Integer pushNotificationTime, boolean isOn) {
        this.id = id;
        this.userId = userId;
        this.pushNotificationTime = pushNotificationTime;
        this.isOn = isOn;
    }

}
