package com.nexters.gaetteok.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

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

    public void doUpdate() {
        this.code = "A";
    }

}
