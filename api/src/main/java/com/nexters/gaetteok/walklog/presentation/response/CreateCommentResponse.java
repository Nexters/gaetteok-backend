package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Schema(description = "댓글 생성 응답")
public class CreateCommentResponse {

    @Schema(description = "댓글 내용", example = "멋진 산책이네요!")
    private final String content;

    @Schema(description = "산책 기록 ID", example = "1")
    private final long walkLogId;

    @Schema(description = "댓글 작성 시간", example = "2021-08-01T12:00:00")
    private final LocalDateTime createdAt;

    @Schema(description = "댓글 작성자 닉네임", example = "뽀삐")
    private String writerNickname;

    @Schema(description = "댓글 작성자 프로필 이미지 URL", example = "https://example.com/profile.jpg")
    private String writerProfileImageUrl;

    @Builder
    public CreateCommentResponse(String content, long walkLogId, String writerNickname,
        String writerProfileImageUrl, LocalDateTime createdAt) {
        this.content = content;
        this.walkLogId = walkLogId;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.createdAt = createdAt;
    }


    public static CreateCommentResponse of(Comment comment) {
        return CreateCommentResponse.builder()
            .content(comment.getContent())
            .walkLogId(comment.getWalkLogId())
            .writerNickname(comment.getWriterNickname())
            .writerProfileImageUrl(comment.getWriterProfileImageUrl())
            .content(comment.getContent())
            .createdAt(comment.getCreatedAt())
            .build();
    }
}
