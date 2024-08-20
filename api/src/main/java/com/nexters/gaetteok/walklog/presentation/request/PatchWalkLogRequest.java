package com.nexters.gaetteok.walklog.presentation.request;

import com.nexters.gaetteok.domain.WalkTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PatchWalkLogRequest {

    @Schema(description = "산책 기록 수정 제목", example = "오늘 산책한 썰 푼다.")
    private String title;

    @Schema(description = "산책 기록 수정 내용", example = "오늘 산책 오졌다 ㅇㅈ?")
    private String content;

    @Schema(description = "산책 시간 수정 내용", example = "WITHIN_20_MINUTES")
    private WalkTime walkTime;

    public PatchWalkLogRequest(String title, String content, WalkTime walkTime) {
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
    }

}
