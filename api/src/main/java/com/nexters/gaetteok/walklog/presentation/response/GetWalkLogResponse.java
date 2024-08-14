package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetWalkLogResponse {

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
    public GetWalkLogResponse(long id, String photoUrl, String title, String content,
        WalkTime walkTime, String writerNickname, String writerProfileImageUrl,
        List<Comment> comments,
        LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public static GetWalkLogResponse of(WalkLog walkLog) {
        return GetWalkLogResponse.builder()
            .id(walkLog.getId())
            .photoUrl(walkLog.getPhotoUrl())
            .title(walkLog.getTitle())
            .content(walkLog.getContent())
            .walkTime(walkLog.getWalkTime())
            .writerNickname(walkLog.getWriterNickname())
            .writerProfileImageUrl(walkLog.getWriterProfileImageUrl())
            .comments(walkLog.getComments())
            .createdAt(walkLog.getCreatedAt())
            .build();
    }

}
