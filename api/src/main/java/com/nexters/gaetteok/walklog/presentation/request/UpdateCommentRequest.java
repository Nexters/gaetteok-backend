package com.nexters.gaetteok.walklog.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UpdateCommentRequest {

    @Schema(description = "댓글 내용", example = "댓글 내용입니다.")
    private String content;

    public UpdateCommentRequest(String content) {
        this.content = content;
    }

}
