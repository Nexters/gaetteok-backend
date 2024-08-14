package com.nexters.gaetteok.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Comment {

    private long id;

    private String content;

    private long writerId;

    private long walkLogId;

    private LocalDateTime createdAt;

    @Builder
    public Comment(long id, String content, long writerId, long walkLogId,
        LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.walkLogId = walkLogId;
        this.createdAt = createdAt;
    }
}
