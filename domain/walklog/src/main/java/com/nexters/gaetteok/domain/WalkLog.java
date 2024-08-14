package com.nexters.gaetteok.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WalkLog {

    private long id;

    private String photoUrl;

    private String title;

    private String content;

    private WalkTime walkTime;

    private String writerNickname;

    private String writerProfileImageUrl;

    private List<Comment> comments;

    private LocalDateTime createdAt;

    @Builder
    public WalkLog(long id, String photoUrl, String title, String content, WalkTime walkTime,
        String writerNickname, String writerProfileImageUrl, LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.createdAt = createdAt;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
