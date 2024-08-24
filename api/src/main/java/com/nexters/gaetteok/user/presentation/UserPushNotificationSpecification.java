package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.user.presentation.response.GetUserPushNotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "UserPushNotification", description = "사용자 푸시 알림 설정 API")
public interface UserPushNotificationSpecification {

    @Operation(summary = "사용자 푸시 알림 설정 조회")
    @ApiResponse(
        responseCode = "200",
        description = "사용자 푸시 알림 설정 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GetUserPushNotificationResponse.class)
        )
    )
    ResponseEntity<GetUserPushNotificationResponse> get(
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "사용자 푸시 알림 설정 수정")
    @ApiResponse(
        responseCode = "200",
        description = "사용자 푸시 알림 설정 수정 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GetUserPushNotificationResponse.class)
        )
    )
    ResponseEntity<GetUserPushNotificationResponse> update(
        @Parameter(description = "알람시간. 시/분을 분으로 환산한 값", example = "540은 아침 9시") Integer pushNotificationTime,
        @Parameter(hidden = true) UserInfo userInfo
    );

}
