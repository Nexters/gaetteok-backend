package com.nexters.gaetteok.walklog.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportWalkLogRequest {

    @Schema(description = "신고 대상 산책 기록 ID", example = "1")
    private long reportedWalkLogId;

    @Schema(description = "신고 사유", example = "욕설")
    private String reason;

    @Builder
    public ReportWalkLogRequest(long reportedWalkLogId, String reason) {
        this.reportedWalkLogId = reportedWalkLogId;
        this.reason = reason;
    }

}
