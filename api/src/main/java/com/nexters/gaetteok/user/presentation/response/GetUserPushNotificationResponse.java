package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.UserPushNotification;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserPushNotificationResponse {

    private Integer pushNotificationTime;

    private boolean isOn;

    @Builder
    public GetUserPushNotificationResponse(Integer pushNotificationTime, boolean isOn) {
        this.pushNotificationTime = pushNotificationTime;
        this.isOn = isOn;
    }

    public static GetUserPushNotificationResponse of(UserPushNotification userPushNotification) {
        return GetUserPushNotificationResponse.builder()
            .pushNotificationTime(userPushNotification.getPushNotificationTime())
            .isOn(userPushNotification.isOn())
            .build();

    }
}
