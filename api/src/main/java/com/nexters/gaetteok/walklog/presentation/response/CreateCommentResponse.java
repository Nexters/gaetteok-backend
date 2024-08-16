package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.Comment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateCommentResponse {

    private final String content;

    private final long walkLogId;
    private final LocalDateTime createdAt;
    private String writerNickname;
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
