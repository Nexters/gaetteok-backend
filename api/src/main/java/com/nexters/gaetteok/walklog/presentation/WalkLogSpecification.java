package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.common.auth.UserInfo;
import com.nexters.gaetteok.walklog.presentation.request.CreateWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.request.PatchWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "WalkLog", description = "산책 기록 API")
public interface WalkLogSpecification {

    @Operation(summary = "산책 기록 생성", description = "산책 기록을 생성하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "산책 기록 생성 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CreateWalkLogResponse.class)
        )
    )
    ResponseEntity<CreateWalkLogResponse> create(
        CreateWalkLogRequest request,
        MultipartFile photo,
        @Parameter(hidden = true) UserInfo userInfo
    ) throws IOException;

    @Operation(summary = "산책 기록 캘린더 조회", description = "특정 사용자의 특정 연, 월의 산책 기록을 조회하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "산책 기록 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = WalkLogCalendarResponse.class)
        )
    )
    ResponseEntity<WalkLogCalendarResponse> getCalendar(
        @Parameter(description = "사용자 아이디") long userId,
        @Parameter(description = "년도") int year,
        @Parameter(description = "월") int month,
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "산책 기록 목록 조회 - 피드용 최신순 조회", description = "전체 또는 특정 사용자의 산책 기록 목록을 최신순으로 조회하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "산책 기록 리스트 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GetWalkLogListResponse.class)
        )
    )
    ResponseEntity<GetWalkLogListResponse> getList(
        @Parameter(description = "커서. 해당 아이디값보다 아이디가 작은 산책 기록을 조회. 없으면 가장 최근 것부터 조회") long cursorId,
        @Parameter(description = "한 번에 조회할 산책 기록 수. 기본값은 10개", example = "10") int pageSize,
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "산책 기록 목록 조회 - 포커스 유저용 월별 그룹 조회", description = "전체 또는 특정 사용자의 산책 기록 목록을 월별 그룹화로 조회하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "산책 기록 리스트 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GetWalkLogListResponse.class)
        )
    )
    ResponseEntity<GetWalkLogListGroupByMonthResponse> getListByUserIdAndMonth(
        @Parameter(description = "사용자 아이디") long userId,
        @Parameter(description = "년도") int year,
        @Parameter(description = "월") int month
    );

    @Operation(summary = "내 산책 기록 리스트 조회", description = "내 산책 기록 리스트를 조회합니다.")
    @ApiResponse(
        responseCode = "200",
        description = "내 산책 기록 리스트 조회 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GetWalkLogListGroupByMonthResponse.class)
        )
    )
    ResponseEntity<GetWalkLogListGroupByMonthResponse> getMyList(
        @Parameter(description = "년도") int year,
        @Parameter(description = "월") int month,
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "산책 기록 변경", description = "산책 기록을 변경합니다.")
    @ApiResponse(
        responseCode = "200",
        description = "산책 기록 변경 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = PatchWalkLogResponse.class)
        )
    )
    ResponseEntity<PatchWalkLogResponse> update(
        @Parameter(description = "산책 기록 ID") long id,
        @Parameter(description = "요청 데이터") PatchWalkLogRequest request,
        @Parameter(description = "산책 기록 사진") MultipartFile photo,
        @Parameter(hidden = true) UserInfo userInfo
    ) throws IOException;

}
