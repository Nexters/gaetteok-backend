package com.nexters.gaetteok.user.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {

    private long id;

    private String nickname;

    private String profileUrl;

    private String code;

    private LocalDateTime createdAt;

    @Builder
    public User(long id, String nickname, String profileUrl, String code, LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.code = code;
        this.createdAt = createdAt;
    }

}
