package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.jwt.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {

    @Schema(description = "댓글 ID", example = "1")
    private long id;

    @Schema(description = "댓글 내용", example = "댓글 내용")
    private String content;

    @Schema(description = "작성자 ID", example = "1")
    private long writerId;

    @Schema(description = "작성자 닉네임", example = "뽀삐")
    private String writerNickname;

    @Schema(description = "작성자 프로필 이미지 URL", example = "https://gaetteok.com/profile.jpg")
    private String writerProfileImageUrl;

    @Schema(description = "산책 기록 ID", example = "1")
    private long walkLogId;

    @Schema(description = "작성자가 본인인지 여부", example = "true")
    private boolean isMe;

    @Schema(description = "작성일", example = "2021-07-01T00:00:00")
    private LocalDateTime createdAt;

    @Builder
    public GetCommentResponse(long id, String content, long writerId, String writerNickname, String writerProfileImageUrl,
                              long walkLogId, boolean isMe, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.walkLogId = walkLogId;
        this.isMe = isMe;
        this.createdAt = createdAt;
    }

    public static GetCommentResponse of(Comment comment, UserInfo userInfo) {
        return GetCommentResponse.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .writerId(comment.getWriterId())
            .writerNickname(comment.getWriterNickname())
            .writerProfileImageUrl(comment.getWriterProfileImageUrl())
            .walkLogId(comment.getWalkLogId())
            .isMe(comment.getWriterId() == userInfo.getUserId())
            .createdAt(comment.getCreatedAt())
            .build();
    }

}
