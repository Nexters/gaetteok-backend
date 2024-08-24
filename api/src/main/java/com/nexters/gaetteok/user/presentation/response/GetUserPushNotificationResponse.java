package com.nexters.gaetteok.user.presentation.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserPushNotificationResponse {

    private Integer pushNotificationTime;

    @Builder
    public GetUserPushNotificationResponse(Integer pushNotificationTime) {
        this.pushNotificationTime = pushNotificationTime;
    }

    public static GetUserPushNotificationResponse of(Integer pushNotificationTime) {
        return GetUserPushNotificationResponse.builder()
            .pushNotificationTime(pushNotificationTime)
            .build();

    }
}
