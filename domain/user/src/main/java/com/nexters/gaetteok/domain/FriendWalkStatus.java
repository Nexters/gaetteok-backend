package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FriendWalkStatus {

    private long id;

    private String nickname;

    private String profileImageUrl;

    private boolean done;

    @Builder
    public FriendWalkStatus(long id, String nickname, String profileImageUrl, boolean done) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.done = done;
    }

}
