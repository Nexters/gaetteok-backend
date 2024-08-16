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

    private String writerNickname;

    private String writerProfileImageUrl;

    private long walkLogId;

    private LocalDateTime createdAt;

    @Builder

    public Comment(long id, String content, String writerNickname, String writerProfileImageUrl,
        long walkLogId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.walkLogId = walkLogId;
        this.createdAt = createdAt;
    }
}
