package com.nexters.gaetteok.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class WalkLog {

    private long id;

    private String photoUrl;

    private String title;

    private String content;

    private WalkTime walkTime;

    private long writerId;

    private String writerNickname;

    private String writerProfileImageUrl;

    private List<Comment> comments;

    private List<Reaction> reactions;

    private LocalDateTime createdAt;

    @Builder
    public WalkLog(long id, String photoUrl, String title, String content, WalkTime walkTime,
                   long writerId, String writerNickname, String writerProfileImageUrl, LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.createdAt = createdAt;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

}
