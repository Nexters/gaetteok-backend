package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class User {

    private long id;

    private String oauthIdentifier;

    private String deviceToken;

    private String nickname;

    private String profileUrl;

    private String code;

    private String location;

    private LocalDateTime createdAt;

    private UserPushNotification userPushNotification;

    @Builder
    public User(
        long id,
        String oauthIdentifier,
        String deviceToken,
        String nickname,
        String profileUrl,
        String code,
        String location,
        LocalDateTime createdAt,
        UserPushNotification userPushNotification
    ) {
        this.id = id;
        this.oauthIdentifier = oauthIdentifier;
        this.deviceToken = deviceToken;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.code = code;
        this.location = location;
        this.createdAt = createdAt;
        this.userPushNotification = userPushNotification;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfile(String profile) {
        this.profileUrl = profile;
    }

    public void updateLocation(String location) {
        this.location = location;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

}
