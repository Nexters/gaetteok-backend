package com.nexters.gaetteok.user.controller.response;

import com.nexters.gaetteok.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUserResponse {

    private final long id;

    private final String nickname;

    private final String profileUrl;

    private final String code;

    private final LocalDateTime createdAt;

    @Builder
    public GetUserResponse(long id, String nickname, String profileUrl, String code, LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.code = code;
        this.createdAt = createdAt;
    }

    public static GetUserResponse of(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profileUrl(user.getProfileUrl())
                .code(user.getCode())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
