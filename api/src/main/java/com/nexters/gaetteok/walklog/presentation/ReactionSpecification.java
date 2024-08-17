package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.walklog.presentation.request.CreateReactionRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateReactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Reaction", description = "산책 기록에 대한 리액션을 생성하고 삭제하는 API")
public interface ReactionSpecification {

    @Operation(summary = "리액션 생성", description = "산책 기록에 대한 리액션을 생성합니다.")
    @ApiResponse(
        responseCode = "200",
        description = "리액션 생성 성공",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CreateReactionResponse.class)
        )
    )
    ResponseEntity<CreateReactionResponse> create(
        @Parameter(description = "산책 기록 아이디") long walkLogId,
        @RequestBody CreateReactionRequest request,
        @Parameter(hidden = true) UserInfo userInfo
    );

    @Operation(summary = "리액션 삭제", description = "산책 기록에 대한 리액션을 삭제합니다.")
    @ApiResponse(
        responseCode = "204",
        description = "리액션 삭제 성공"
    )
    ResponseEntity<Void> delete(
        @Parameter(description = "산책 기록 아이디") long walkLogId,
        @Parameter(description = "리액션 아이디") long reactionId,
        @Parameter(hidden = true) UserInfo userInfo
    );

}
