package com.nexters.gaetteok.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserPushNotification {

    private long id;

    private long userId;

    private long pushNotificationTime;

}
