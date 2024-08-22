package com.nexters.gaetteok.common.presentation.request;

import com.nexters.gaetteok.domain.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class CreateReactionAdminRequest {

    private long userId;

    private long walkLogId;

    private ReactionType reactionType;

    private LocalDateTime createdAt;

}
