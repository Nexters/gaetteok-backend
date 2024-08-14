package com.nexters.gaetteok.walklog.presentation.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CreateCommentRequest {

    private String content;

    public CreateCommentRequest(String content) {
        this.content = content;
    }

}
