package com.nexters.gaetteok.walklog.presentation.request;

import com.nexters.gaetteok.domain.ReactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CreateReactionRequest {

    @Schema(description = "반응 타입", examples = {
        "LIKE",
        "CONGRATULATION",
        "IMPRESSIVE",
        "SAD",
        "ANGRY"
    })
    private ReactionType reactionType;

    public CreateReactionRequest(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

}
