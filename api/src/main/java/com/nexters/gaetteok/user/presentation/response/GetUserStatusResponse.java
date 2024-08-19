package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.weather.enums.City;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserStatusResponse {

    @Schema(description = "유저 ID", example = "1")
    private final long id;

    @Schema(description = "유저 닉네임", example = "뽀삐")
    private final String nickname;

    @Schema(description = "유저 프로필 이미지 URL", example = "https://profile.com/1")
    private final String profileImageUrl;

    @Schema(description = "유저 코드", example = "1a2b3c")
    private final String code;

    @Schema(description = "산책 완료 여부", example = "true")
    private final boolean walkDone;

    @Schema(description = "메인 화면 이미지 URL", example = "https://main.com/wait.jpg")
    private final String mainScreenImageUrl;

    @Schema(description = "유저 지역 정보", example = "SEOUL")
    private final City location;

    @Schema(description = "유저 생성일", example = "2021-08-01T00:00:00")
    private final LocalDateTime createdAt;

    @Builder
    public GetUserStatusResponse(long id, String nickname, String profileImageUrl, String code,
        boolean walkDone, String mainScreenImageUrl, City location, LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.code = code;
        this.walkDone = walkDone;
        this.mainScreenImageUrl = mainScreenImageUrl;
        this.location = location;
        this.createdAt = createdAt;
    }

    public static GetUserStatusResponse of(User user, boolean walkDone, String mainScreenImageUrl) {
        return GetUserStatusResponse.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .profileImageUrl(user.getProfileUrl())
            .code(user.getCode())
            .walkDone(walkDone)
            .mainScreenImageUrl(mainScreenImageUrl)
            .location(City.valueOf(user.getLocation()))
            .createdAt(user.getCreatedAt())
            .build();
    }

}
