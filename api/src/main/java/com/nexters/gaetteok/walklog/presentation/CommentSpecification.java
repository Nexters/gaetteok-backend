package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.request.UpdateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateCommentResponse;
import com.nexters.gaetteok.walklog.presentation.response.UpdateCommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

    @Operation(summary = "댓글 수정", description = "산책 기록에 댓글을 수정합니다.")
    @ApiResponse(
        responseCode = "200",
        description = "댓글 수정 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = UpdateCommentResponse.class)
        )
    )
    ResponseEntity<UpdateCommentResponse> update(
        @Parameter(description = "댓글 고유 ID") long id,
        @RequestBody UpdateCommentRequest request
    ) throws IOException;

    @Operation(summary = "댓글 삭제", description = "산책 기록에 댓글을 삭제합니다.")
    @ApiResponse(
        responseCode = "200",
        description = "댓글 삭제 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Void.class)
        )
    )
    ResponseEntity<Void> delete(
        @Parameter(description = "댓글 고유 ID") long id,
        @Parameter(hidden = true) UserInfo userInfo
    ) throws IOException;

}
