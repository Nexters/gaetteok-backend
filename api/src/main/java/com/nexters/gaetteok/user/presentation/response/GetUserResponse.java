package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUserResponse {

    @Schema(description = "유저 ID", example = "1")
    private final long id;

    @Schema(description = "유저 닉네임", example = "뽀삐")
    private final String nickname;

    @Schema(description = "유저 프로필 이미지 URL", example = "https://profile.com/1")
    private final String profileImageUrl;

    @Schema(description = "유저 코드", example = "1a2b3c")
    private final String code;

    @Schema(description = "유저 생성일", example = "2021-08-01T00:00:00")
    private final LocalDateTime createdAt;

    @Builder
    public GetUserResponse(long id, String nickname, String profileImageUrl, String code,
        LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.code = code;
        this.createdAt = createdAt;
    }

    public static GetUserResponse of(User user) {
        return GetUserResponse.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .profileImageUrl(user.getProfileUrl())
            .code(user.getCode())
            .createdAt(user.getCreatedAt())
            .build();
    }

}
