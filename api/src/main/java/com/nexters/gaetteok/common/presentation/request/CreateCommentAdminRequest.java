package com.nexters.gaetteok.common.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class CreateCommentAdminRequest {

    private long walkLogId;

    private String content;

    private long userId;

    private LocalDateTime createdAt;

}
