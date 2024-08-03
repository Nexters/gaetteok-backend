package com.nexters.gaetteok.user.presentation.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserPushNotificationResponse {

    private long pushNotificationTime;

    @Builder
    public GetUserPushNotificationResponse(long pushNotificationTime) {
        this.pushNotificationTime = pushNotificationTime;
    }

    public static GetUserPushNotificationResponse of(long pushNotificationTime) {
        return GetUserPushNotificationResponse.builder()
            .pushNotificationTime(pushNotificationTime)
            .build();

    }
}
