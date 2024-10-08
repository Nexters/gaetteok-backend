package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.Reaction;
import com.nexters.gaetteok.domain.ReactionType;
import com.nexters.gaetteok.jwt.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetReactionResponse {

    @Schema(description = "리액션 ID", example = "1")
    private long id;

    @Schema(description = "작성자 ID", example = "1")
    private long writerId;

    @Schema(description = "작성자 닉네임", example = "뽀삐")
    private String writerNickname;

    @Schema(description = "작성자 프로필 이미지 URL", example = "https://gaetteok.com/profile.jpg")
    private String writerProfileImageUrl;

    @Schema(description = "리액션 타입", example = "LIKE")
    private ReactionType reactionType;

    @Schema(description = "산책 기록 ID", example = "1")
    private long walkLogId;

    @Schema(description = "작성자가 본인인지 여부", example = "true")
    private boolean isMe;

    @Schema(description = "작성일", example = "2021-07-01T00:00:00")
    private LocalDateTime createdAt;

    @Builder
    public GetReactionResponse(long id, long writerId, String writerNickname, String writerProfileImageUrl,
                               ReactionType reactionType, long walkLogId, boolean isMe, LocalDateTime createdAt) {
        this.id = id;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.reactionType = reactionType;
        this.walkLogId = walkLogId;
        this.isMe = isMe;
        this.createdAt = createdAt;
    }

    public static GetReactionResponse of(Reaction reaction, UserInfo userInfo) {
        return GetReactionResponse.builder()
            .id(reaction.getId())
            .writerId(reaction.getWriterId())
            .writerNickname(reaction.getWriterNickname())
            .writerProfileImageUrl(reaction.getWriterProfileImageUrl())
            .reactionType(reaction.getReactionType())
            .walkLogId(reaction.getWalkLogId())
            .isMe(reaction.getWriterId() == userInfo.getUserId())
            .createdAt(reaction.getCreatedAt())
            .build();
    }

}
