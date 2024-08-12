package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FriendWalkStatus {

    private long id;

    private String nickname;

    private String profileUrl;

    private boolean done;

    @Builder
    public FriendWalkStatus(long id, String nickname, String profileUrl, boolean done) {
        this.id = id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.done = done;
    }

}
