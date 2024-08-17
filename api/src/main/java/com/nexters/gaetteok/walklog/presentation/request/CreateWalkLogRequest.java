package com.nexters.gaetteok.walklog.presentation.request;

import com.nexters.gaetteok.domain.WalkTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateWalkLogRequest {

    @Schema(description = "산책 기록 제목", example = "산책 기록 제목")
    private String title;

    @Schema(description = "산책 기록 내용", example = "산책 기록 내용")
    private String content;

    @Schema(description = "산책 시간", examples = {"20분 내외", "20분~40분", "40분~1시간"})
    private WalkTime walkTime;

    public CreateWalkLogRequest(String title, String content, WalkTime walkTime) {
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
    }

}
