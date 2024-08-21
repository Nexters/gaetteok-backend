package com.nexters.gaetteok.walklog.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexters.gaetteok.domain.WalkLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class WalkLogCalendarResponse {

    @JsonProperty("items")
    @Schema(description = "산책 기록 월별 리스트")
    private Map<String, GetWalkLogResponse> walkLogs;

    public WalkLogCalendarResponse(Map<String, WalkLog> walkLogs) {
        this.walkLogs = walkLogs.entrySet().stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> GetWalkLogResponse.of(entry.getValue())
                )
            );
    }

    public static WalkLogCalendarResponse of(Map<String, WalkLog> walkLogs) {
        return new WalkLogCalendarResponse(walkLogs);
    }
}
