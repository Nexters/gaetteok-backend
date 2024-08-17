package com.nexters.gaetteok.user.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SignupResponse {

    @Schema(description = "사용자 jwt 토큰. Bearer 키워드와 함께 Authorization 헤더에 동봉")
    private String token;

    public SignupResponse(String token) {
        this.token = token;
    }

}
