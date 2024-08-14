package com.nexters.gaetteok.walklog.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexters.gaetteok.domain.WalkLog;
import java.util.Map;
import lombok.Getter;

@Getter
public class WalkLogCalendarResponse {

    @JsonProperty("items")
    private Map<String, WalkLog> walkLogs;

    public WalkLogCalendarResponse(Map<String, WalkLog> walkLogs) {
        this.walkLogs = walkLogs;
    }

    public static WalkLogCalendarResponse of(Map<String, WalkLog> walkLogs) {
        return new WalkLogCalendarResponse(walkLogs);
    }
}
