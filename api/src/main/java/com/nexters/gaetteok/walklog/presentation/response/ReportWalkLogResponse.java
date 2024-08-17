package com.nexters.gaetteok.walklog.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportWalkLogResponse {

    @Schema(description = "신고 ID", example = "1")
    private long id;

    @Schema(description = "신고 시간", example = "2021-08-01T00:00:00")
    private LocalDateTime createdAt;

    @Builder
    public ReportWalkLogResponse(long id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public static ReportWalkLogResponse of(long id, LocalDateTime createdAt) {
        return ReportWalkLogResponse.builder()
            .id(id)
            .createdAt(createdAt)
            .build();
    }

}
