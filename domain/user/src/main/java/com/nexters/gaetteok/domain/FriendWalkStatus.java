package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FriendWalkStatus {

    private String nickname;

    private String profileUrl;

    private boolean done;

    @Builder
    public FriendWalkStatus(String nickname, String profileUrl, boolean done) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.done = done;
    }

}
