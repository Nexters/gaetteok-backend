package com.nexters.gaetteok.walklog.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportCommentResponse {

    @Schema(description = "신고 ID", example = "1")
    private long id;

    @Schema(description = "신고 사유", example = "욕설")
    private String reason;

    @Schema(description = "신고 시간", example = "2021-08-01T00:00:00")
    private LocalDateTime createdAt;

    @Builder
    public ReportCommentResponse(long id, String reason, LocalDateTime createdAt) {
        this.id = id;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public static ReportCommentResponse of(long id, String reason, LocalDateTime createdAt) {
        return ReportCommentResponse.builder()
            .id(id)
            .reason(reason)
            .createdAt(createdAt)
            .build();
    }

}
