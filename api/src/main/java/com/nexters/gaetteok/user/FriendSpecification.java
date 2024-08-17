package com.nexters.gaetteok.user;

import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.user.constant.SortCondition;
import com.nexters.gaetteok.user.presentation.request.CreateFriendRequest;
import com.nexters.gaetteok.user.presentation.response.CreateFriendResponse;
import com.nexters.gaetteok.user.presentation.response.GetFriendListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
        @Parameter(description = "정렬 조건", examples = {
            @ExampleObject(name = "FRIEND_DESC", value = "친구 등록 내림차순"),
            @ExampleObject(name = "WALK_DONE_DESC", value = "산책 완료 시간 내림차순")
        }) SortCondition sortCondition,
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "친구 삭제", description = "친구를 삭제하는 API")
    @ApiResponse(
        responseCode = "204",
        description = "친구 삭제 성공"
    )
    ResponseEntity<Void> delete(
        @Parameter(description = "친구의 사용자 아이디") long friendUserId,
        @Parameter(hidden = true) UserInfo userInfo
    );

}
