package com.nexters.gaetteok.walklog.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexters.gaetteok.domain.WalkLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class GetWalkLogListResponse {

    @JsonProperty("items")
    @Schema(description = "산책 기록 리스트")
    private List<GetWalkLogResponse> walkLogList;

    public GetWalkLogListResponse(List<GetWalkLogResponse> walkLogList) {
        this.walkLogList = walkLogList;
    }

    public static GetWalkLogListResponse of(List<WalkLog> walkLogList) {
        return new GetWalkLogListResponse(walkLogList.stream().map(GetWalkLogResponse::of).toList());
    }

}
