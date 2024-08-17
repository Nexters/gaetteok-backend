package com.nexters.gaetteok.walklog.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexters.gaetteok.domain.WalkLog;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class GetWalkLogListGroupByMonthResponse {

    @Schema(description = "조회한 년도")
    private int year;

    @Schema(description = "조회한 월")
    private int month;

    @Schema(description = "산책 기록 개수")
    private int count;

    @Schema(description = "다음 데이터의 년도")
    private int nextYear;

    @Schema(description = "다음 데이터의 월")
    private int nextMonth;

    @Schema(description = "다음 데이터의 존재 여부")
    private boolean hasNext;

    @JsonProperty("items")
    @Schema(description = "산책 기록 리스트")
    private List<GetWalkLogResponse> walkLogList;

    @Builder
    public GetWalkLogListGroupByMonthResponse(int year, int month, int count, int nextYear, int nextMonth, boolean hasNext, List<GetWalkLogResponse> walkLogList) {
        this.year = year;
        this.month = month;
        this.count = count;
        this.nextYear = nextYear;
        this.nextMonth = nextMonth;
        this.hasNext = hasNext;
        this.walkLogList = walkLogList;
    }

    public static GetWalkLogListGroupByMonthResponse of(int year, int month, @Nullable WalkLog nextData, List<WalkLog> walkLogList) {
        boolean hasNext = nextData != null;
        return GetWalkLogListGroupByMonthResponse.builder()
            .year(year)
            .month(month)
            .count(walkLogList.size())
            .hasNext(hasNext)
            .nextYear(hasNext ? nextData.getCreatedAt().getYear() : 0)
            .nextMonth(hasNext ? nextData.getCreatedAt().getMonthValue() : 0)
            .walkLogList(walkLogList.stream().map(GetWalkLogResponse::of).toList())
            .build();
    }

}
