package com.nexters.gaetteok.user.presentation.request;

import com.nexters.gaetteok.domain.AuthProvider;
import com.nexters.gaetteok.weather.enums.City;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@ToString
public class SignupRequest {

    @Schema(description = "로그인 토큰", example = "kakao_token")
    private String oauthIdentifier;

    @Schema(description = "디바이스 토큰", example = "device_token")
    private String deviceToken;

    @Schema(description = "사용자 닉네임", example = "닉네임")
    private String nickname;

    @Schema(description = "프로필 이미지 URL", example = "https://profile.com")
    private String profileUrl;

    @Schema(description = "사용자 위치 정보", example = "SEOUL")
    private City city;

    @Schema(description = "인증 제공자", examples = {"KAKAO", "APPLE"})
    private AuthProvider provider;

    @Builder
    public SignupRequest(String oauthIdentifier, String deviceToken, String nickname, String profileUrl, City city, AuthProvider provider) {
        this.oauthIdentifier = oauthIdentifier;
        this.deviceToken = deviceToken;
        this.nickname = StringUtils.hasText(nickname) ? nickname : "포포";
        this.profileUrl = profileUrl;
        this.city = city;
        this.provider = provider;
    }

}
