package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PatchWalkLogResponse {

    private final long id;

    private final String photoUrl;
    private final String content;
    private final WalkTime walkTime;
    private final String writerNickname;
    private final String writerProfileImageUrl;
    private final LocalDateTime createdAt;
    private String title;

    @Builder
    public PatchWalkLogResponse(long id, String photoUrl, String title, String content,
        WalkTime walkTime, String writerNickname, String writerProfileImageUrl,
        LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.createdAt = createdAt;
    }

    public static PatchWalkLogResponse of(WalkLog walkLog) {
        return PatchWalkLogResponse.builder()
            .id(walkLog.getId())
            .photoUrl(walkLog.getPhotoUrl())
            .title(walkLog.getTitle())
            .content(walkLog.getContent())
            .walkTime(walkLog.getWalkTime())
            .writerNickname(walkLog.getWriterNickname())
            .writerProfileImageUrl(walkLog.getWriterProfileImageUrl())
            .createdAt(walkLog.getCreatedAt())
            .build();
    }

}
