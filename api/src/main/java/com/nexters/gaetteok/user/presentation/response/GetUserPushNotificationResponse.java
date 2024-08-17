package com.nexters.gaetteok.user.presentation.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserPushNotificationResponse {

    private long pushNotificationTime;

    @Builder
    public GetUserPushNotificationResponse(int pushNotificationTime) {
        this.pushNotificationTime = pushNotificationTime;
    }

    public static GetUserPushNotificationResponse of(int pushNotificationTime) {
        return GetUserPushNotificationResponse.builder()
            .pushNotificationTime(pushNotificationTime)
            .build();

    }
}
