package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetWalkLogResponse {

    private long id;

    private String photoUrl;

    private String title;

    private String content;

    private WalkTime walkTime;

    private String writerNickname;

    private String writerProfileUrl;

    private LocalDateTime createdAt;

    @Builder
    public GetWalkLogResponse(long id, String photoUrl, String title, String content, WalkTime walkTime, String writerNickname, String writerProfileUrl, LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerNickname = writerNickname;
        this.writerProfileUrl = writerProfileUrl;
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
                .writerProfileUrl(walkLog.getWriterProfileUrl())
                .createdAt(walkLog.getCreatedAt())
                .build();
    }

}
