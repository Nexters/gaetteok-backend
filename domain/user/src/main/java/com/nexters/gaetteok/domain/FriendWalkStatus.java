package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FriendWalkStatus {

    private long id;

    private String nickname;

    private String profileImageUrl;

    private boolean done;

    private LocalDateTime friendCreatedAt;

    private LocalDateTime lastWalkDate;

    @Builder
    public FriendWalkStatus(long id, String nickname, String profileImageUrl, boolean done, LocalDateTime friendCreatedAt, LocalDateTime lastWalkDate) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.done = done;
        this.friendCreatedAt = friendCreatedAt;
        this.lastWalkDate = lastWalkDate;
    }

}
