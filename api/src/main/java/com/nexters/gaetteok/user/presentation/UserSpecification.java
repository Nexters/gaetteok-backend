package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.common.auth.UserInfo;
import com.nexters.gaetteok.user.presentation.response.GetUserResponse;
import com.nexters.gaetteok.user.presentation.response.UpdateUserLocationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "User", description = "유저 API")
public interface UserSpecification {

    @Operation(summary = "유저 조회", description = "유저 정보를 조회하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "유저 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GetUserResponse.class)
        )
    )
    ResponseEntity<GetUserResponse> getUser(
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "유저 위치 수정", description = "유저의 위치를 수정하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "유저 위치 수정 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = UpdateUserLocationResponse.class)
        )
    )
    ResponseEntity<UpdateUserLocationResponse> updateLocation(
        @Parameter(description = "변경할 위치 정보", example = "서울") String location,
        @Parameter(hidden = true) UserInfo userInfo
    );

}
