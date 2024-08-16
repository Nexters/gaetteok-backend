package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.common.auth.UserInfo;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateCommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Tag(name = "Comment", description = "댓글 API")
public interface CommentSpecification {

    @Operation(summary = "댓글 생성", description = "산책 기록에 댓글을 생성합니다.")
    @ApiResponse(
        responseCode = "200",
        description = "댓글 생성 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CreateCommentResponse.class)
        )
    )
    ResponseEntity<CreateCommentResponse> create(
        @Parameter(description = "산책 기록 ID") long id,
        @RequestBody CreateCommentRequest request,
        @Parameter(hidden = true) UserInfo userInfo
    ) throws IOException;

}
