package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.User;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserResponse {

    private final long id;

    private final String nickname;

    private final String profileImageUrl;

    private final String code;

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
