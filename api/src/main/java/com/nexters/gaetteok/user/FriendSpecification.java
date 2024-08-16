package com.nexters.gaetteok.user;

import com.nexters.gaetteok.common.auth.UserInfo;
import com.nexters.gaetteok.user.presentation.request.CreateFriendRequest;
import com.nexters.gaetteok.user.presentation.response.CreateFriendResponse;
import com.nexters.gaetteok.user.presentation.response.GetFriendListResponse;
import com.nexters.gaetteok.user.presentation.response.GetFriendWalkStatusListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Friend", description = "친구 API")
public interface FriendSpecification {

    @Operation(summary = "친구 추가", description = "친구 코드로 친구를 추가하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "친구 추가 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CreateFriendResponse.class)
        )
    )
    ResponseEntity<CreateFriendResponse> create(
        @RequestBody CreateFriendRequest request,
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "친구 목록 조회", description = "내 친구 목록을 조회하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "친구 목록 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GetFriendListResponse.class)
        )
    )
    ResponseEntity<GetFriendListResponse> getList(
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "친구 산책 상태 목록 조회", description = "내 친구들의 산책 상태 목록을 조회하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "친구 산책 상태 목록 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GetFriendWalkStatusListResponse.class)
        )
    )
    ResponseEntity<GetFriendWalkStatusListResponse> getWalkStatusList(
        @Parameter(hidden = true) UserInfo userInfo
    );

}
