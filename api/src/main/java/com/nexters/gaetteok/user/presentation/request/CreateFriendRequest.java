package com.nexters.gaetteok.user.presentation.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateFriendRequest {

    private String code;

    public CreateFriendRequest(String code) {
        this.code = code;
    }

}
