package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.WalkLog;
import lombok.Getter;

import java.util.List;

@Getter
public class GetWalkLogListResponse {

    private List<GetWalkLogResponse> walkLogList;

    public GetWalkLogListResponse(List<GetWalkLogResponse> walkLogList) {
        this.walkLogList = walkLogList;
    }

    public static GetWalkLogListResponse of(List<WalkLog> walkLogList) {
        return new GetWalkLogListResponse(walkLogList.stream().map(GetWalkLogResponse::of).toList());
    }

}
