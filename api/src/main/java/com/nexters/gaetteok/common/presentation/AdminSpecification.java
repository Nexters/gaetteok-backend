package com.nexters.gaetteok.common.presentation;

import com.nexters.gaetteok.common.presentation.request.CreateCommentAdminRequest;
import com.nexters.gaetteok.common.presentation.request.CreateReactionAdminRequest;
import com.nexters.gaetteok.common.presentation.request.CreateWalkLogAdminRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Admin", description = "관리자용 API")
public interface AdminSpecification {

    @Operation(summary = "관리자용 산책 기록 생성", description = "관리자용 산책 기록을 생성하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "산책 기록 생성 성공"
    )
    ResponseEntity<Void> createWalkLogs(
        @Parameter(hidden = true) String token,
        CreateWalkLogAdminRequest request,
        MultipartFile photo
    ) throws IOException;

    @Operation(summary = "관리자용 댓글 생성", description = "관리자용 댓글을 생성하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "댓글 생성 성공"
    )
    ResponseEntity<Void> createComments(
        @Parameter(hidden = true) String token,
        CreateCommentAdminRequest request
    );

    @Operation(summary = "관리자용 리액션 생성", description = "관리자용 리액션을 생성하는 API")
    @ApiResponse(
        responseCode = "200",
        description = "리액션 생성 성공"
    )
    ResponseEntity<Void> createReactions(
        @Parameter(hidden = true) String token,
        CreateReactionAdminRequest request
    );

}
