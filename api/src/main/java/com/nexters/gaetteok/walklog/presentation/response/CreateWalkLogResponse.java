package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CreateWalkLogResponse {

    private final long id;

    private final String photoUrl;

    private String title;

    private final String content;

    private final WalkTime walkTime;

    private final String writerNickname;

    private final String writerProfileUrl;

    private final LocalDateTime createdAt;

    @Builder
    public CreateWalkLogResponse(long id, String photoUrl, String title, String content, WalkTime walkTime, String writerNickname, String writerProfileUrl, LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerNickname = writerNickname;
        this.writerProfileUrl = writerProfileUrl;
        this.createdAt = createdAt;
    }

    public static CreateWalkLogResponse of(WalkLog walkLog) {
        return CreateWalkLogResponse.builder()
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
