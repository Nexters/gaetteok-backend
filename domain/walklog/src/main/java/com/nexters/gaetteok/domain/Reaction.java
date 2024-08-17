package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Reaction {

    private long id;

    private String writerNickname;

    private String writerProfileImageUrl;

    private ReactionType reactionType;

    private long walkLogId;

    private LocalDateTime createdAt;

    @Builder
    public Reaction(long id, String writerNickname, String writerProfileImageUrl, ReactionType reactionType, long walkLogId, LocalDateTime createdAt) {
        this.id = id;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.reactionType = reactionType;
        this.walkLogId = walkLogId;
        this.createdAt = createdAt;
    }

}
