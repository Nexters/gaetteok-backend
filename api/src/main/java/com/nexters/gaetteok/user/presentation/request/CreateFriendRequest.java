package com.nexters.gaetteok.user.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CreateFriendRequest {

    @Schema(description = "친구 코드", example = "1a2b3c")
    private String code;

    public CreateFriendRequest(String code) {
        this.code = code;
    }

}
