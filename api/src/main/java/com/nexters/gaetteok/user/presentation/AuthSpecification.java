package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.user.presentation.request.SignupRequest;
import com.nexters.gaetteok.user.presentation.response.LoginResponse;
import com.nexters.gaetteok.user.presentation.response.SignupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "사용자 인증 관련 API")
public interface AuthSpecification {

    @Operation(summary = "회원가입")
    @ApiResponse(
        responseCode = "200",
        description = "회원가입 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = SignupResponse.class)
        )
    )
    ResponseEntity<SignupResponse> signup(
        @RequestBody SignupRequest request
    );

    @Operation(summary = "로그인")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "로그인 성공",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = LoginResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "해당 사용자가 존재하지 않음"
        )
    })
    ResponseEntity<LoginResponse> login(
        @Parameter(description = "사용자 토큰") String oauthIdentifier,
        @Parameter(description = "디바이스 토큰") String deviceToken
    );

}
