package com.nexters.gaetteok.walklog.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportCommentRequest {

    @Schema(description = "신고 대상 댓글 ID", example = "1")
    private long reportedCommentId;

    @Schema(description = "신고 사유", example = "욕설")
    private String reason;

    @Builder
    public ReportCommentRequest(long reportedCommentId, String reason) {
        this.reportedCommentId = reportedCommentId;
        this.reason = reason;
    }

}
