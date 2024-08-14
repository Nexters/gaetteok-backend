package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class WalkLog {

    private long id;

    private String photoUrl;

    private String title;

    private String content;

    private WalkTime walkTime;

    private String writerNickname;

    private String writerProfileUrl;

    private LocalDateTime createdAt;

    @Builder
    public WalkLog(long id, String photoUrl, String title, String content, WalkTime walkTime, String writerNickname, String writerProfileUrl, LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerNickname = writerNickname;
        this.writerProfileUrl = writerProfileUrl;
        this.createdAt = createdAt;
    }

}
