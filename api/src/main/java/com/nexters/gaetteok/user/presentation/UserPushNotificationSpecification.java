package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.user.presentation.response.GetUserPushNotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
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
        @Parameter(description = "알람시간. MMDD 형태의 숫자값", example = "0830") int pushNotificationTime,
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "특정 사용자에게 산책 독려 알림 발송")
    @ApiResponse(
        responseCode = "200",
        description = "사용자 푸시 알림 발송 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Void.class)
        )
    )
    ResponseEntity<Void> pushNotification(
        @Parameter(description = "산책 독려 알림을 받을 계정의 ID") long targetUserId
    ) throws BadRequestException;

}
