package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class CreateWalkLogResponse {

    @Schema(description = "산책 기록 ID", example = "1")
    private final long id;

    @Schema(description = "산책 기록 사진 URL", example = "https://gaetteok.com/1.jpg")
    private final String photoUrl;

    @Schema(description = "산책 기록 제목", example = "산책 기록 제목")
    private String title;

    @Schema(description = "산책 기록 내용", example = "산책 기록 내용")
    private final String content;

    @Schema(description = "산책 시간", example = "20분 내외")
    private final WalkTime walkTime;

    @Schema(description = "작성자 닉네임", example = "뽀삐")
    private final String writerNickname;

    @Schema(description = "작성자 프로필 이미지 URL", example = "https://gaetteok.com/profile.jpg")
    private final String writerProfileImageUrl;

    @Schema(description = "작성일", example = "2021-07-01T00:00:00")
    private final LocalDateTime createdAt;

    @Builder
    public CreateWalkLogResponse(long id, String photoUrl, String title, String content,
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

    public static CreateWalkLogResponse of(WalkLog walkLog) {
        return CreateWalkLogResponse.builder()
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
