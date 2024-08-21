package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Comment {

    private long id;

    private String content;

    private long writerId;

    private String writerNickname;

    private String writerProfileImageUrl;

    private long walkLogId;

    private LocalDateTime createdAt;

    @Builder
    public Comment(long id, String content, long writerId, String writerNickname,
                   String writerProfileImageUrl, long walkLogId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.walkLogId = walkLogId;
        this.createdAt = createdAt;
    }

}
