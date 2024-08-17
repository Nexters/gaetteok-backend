package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.domain.Reaction;
import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
public class GetWalkLogResponse {

    @Schema(description = "산책 기록 ID", example = "1")
    private long id;

    @Schema(description = "산책 기록 사진 URL", example = "https://gaetteok.com/1.jpg")
    private String photoUrl;

    @Schema(description = "산책 기록 제목", example = "산책 기록 제목")
    private String title;

    @Schema(description = "산책 기록 내용", example = "산책 기록 내용")
    private String content;

    @Schema(description = "산책 시간", examples = {"20분 내외", "20분~40분", "40분~1시간"})
    private WalkTime walkTime;

    @Schema(description = "작성자 닉네임", example = "뽀삐")
    private String writerNickname;

    @Schema(description = "작성자 프로필 이미지 URL", example = "https://gaetteok.com/profile.jpg")
    private String writerProfileImageUrl;

    @Schema(description = "댓글 리스트")
    private List<Comment> comments;

    @Schema(description = "리액션 리스트")
    private List<Reaction> reactions;

    @Schema(description = "작성일", example = "2021-07-01T00:00:00")
    private LocalDateTime createdAt;

    @Builder
    public GetWalkLogResponse(long id, String photoUrl, String title, String content,
        WalkTime walkTime, String writerNickname, String writerProfileImageUrl,
                              List<Comment> comments, List<Reaction> reactions,
        LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.comments = comments;
        this.reactions = reactions;
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
            .comments(walkLog.getComments() != null ? walkLog.getComments() : Collections.emptyList())
            .reactions(walkLog.getReactions() != null ? walkLog.getReactions() : Collections.emptyList())
            .createdAt(walkLog.getCreatedAt())
            .build();
    }

}
