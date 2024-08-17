package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.Reaction;
import com.nexters.gaetteok.domain.ReactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateReactionResponse {

    @Schema(description = "리액션 아이디", example = "1")
    private long id;

    @Schema(description = "리액션 작성자 닉네임", example = "뽀삐")
    private String writerNickname;

    @Schema(description = "리액션 작성자 프로필 이미지 URL", example = "https://example.com/profile.jpg")
    private String writerProfileImageUrl;

    @Schema(description = "리액션 타입", examples = {
        "LIKE",
        "CONGRATULATION",
        "IMPRESSIVE",
        "SAD",
        "ANGRY"
    })
    private ReactionType reactionType;

    @Schema(description = "리액션 생성 시간", example = "2021-08-01T00:00:00")
    private LocalDateTime createdAt;

    @Builder
    public CreateReactionResponse(long id, String writerNickname, String writerProfileImageUrl, ReactionType reactionType, LocalDateTime createdAt) {
        this.id = id;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.reactionType = reactionType;
        this.createdAt = createdAt;
    }

    public static CreateReactionResponse of(Reaction reaction) {
        return CreateReactionResponse.builder()
            .id(reaction.getId())
            .writerNickname(reaction.getWriterNickname())
            .writerProfileImageUrl(reaction.getWriterProfileImageUrl())
            .reactionType(reaction.getReactionType())
            .createdAt(reaction.getCreatedAt())
            .build();
    }

}
