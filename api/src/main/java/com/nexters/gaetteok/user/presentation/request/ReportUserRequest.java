package com.nexters.gaetteok.user.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportUserRequest {

    @Schema(description = "신고 대상 유저 ID", example = "1")
    private long reportedUserId;

    @Schema(description = "신고 사유", example = "욕설")
    private String reason;

    @Builder
    public ReportUserRequest(long reportedUserId, String reason) {
        this.reportedUserId = reportedUserId;
        this.reason = reason;
    }

}
