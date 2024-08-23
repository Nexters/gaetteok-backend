package com.nexters.gaetteok.common.presentation.request;

import com.nexters.gaetteok.domain.WalkTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class CreateWalkLogAdminRequest {

    private String title;

    private String content;

    private WalkTime walkTime;

    private long userId;

    private LocalDateTime createdAt;

}
