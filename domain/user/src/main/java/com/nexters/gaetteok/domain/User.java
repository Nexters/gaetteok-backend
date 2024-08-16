package com.nexters.gaetteok.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

    private long id;

    private String nickname;

    private String profileUrl;

    private String code;

    private String location;

    private LocalDateTime createdAt;

    private UserPushNotification userPushNotification;

    @Builder
    public User(
        long id,
        String nickname,
        String profileUrl,
        String code,
        String location,
        LocalDateTime createdAt,
        UserPushNotification userPushNotification
    ) {
        this.id = id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.code = code;
        this.location = location;
        this.createdAt = createdAt;
        this.userPushNotification = userPushNotification;
    }

    public void updateLocation(String location) {
        this.location = location;
    }
}
