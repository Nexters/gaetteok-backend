package com.nexters.gaetteok.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reaction {

    private long id;

    private long writerId;

    private String writerNickname;

    private String writerProfileImageUrl;

    private ReactionType reactionType;

    private long walkLogId;

    private LocalDateTime createdAt;

    @Builder
    public Reaction(long id, long writerId, String writerNickname, String writerProfileImageUrl,
                    ReactionType reactionType, long walkLogId, LocalDateTime createdAt) {
        this.id = id;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.reactionType = reactionType;
        this.walkLogId = walkLogId;
        this.createdAt = createdAt;
    }

}
