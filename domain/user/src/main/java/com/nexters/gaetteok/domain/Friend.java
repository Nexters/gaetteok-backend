package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Friend {

    private long id;

    private User me;

    private User friend;

    private LocalDateTime createdAt;

    @Builder
    public Friend(long id, User me, User friend, LocalDateTime createdAt) {
        this.id = id;
        this.me = me;
        this.friend = friend;
        this.createdAt = createdAt;
    }

}
