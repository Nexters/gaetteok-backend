package com.nexters.gaetteok.walklog.presentation.request;

import com.nexters.gaetteok.domain.WalkTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PatchWalkLogRequest {

    private String title;

    private String content;

    public PatchWalkLogRequest(String title, String content, WalkTime walkTime) {
        this.title = title;
        this.content = content;
    }

}
