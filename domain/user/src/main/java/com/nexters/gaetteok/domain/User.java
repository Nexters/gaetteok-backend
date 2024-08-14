package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class User {

    private long id;

    private String nickname;

    private String profileUrl;

    private String code;

    private LocalDateTime createdAt;

    private UserPushNotification userPushNotification;

    @Builder
    public User(
        long id,
        String nickname,
        String profileUrl,
        String code,
        LocalDateTime createdAt,
        UserPushNotification userPushNotification
    ) {
        this.id = id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.code = code;
        this.createdAt = createdAt;
        this.userPushNotification = userPushNotification;
    }
}
