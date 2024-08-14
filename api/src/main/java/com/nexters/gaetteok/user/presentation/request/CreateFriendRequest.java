package com.nexters.gaetteok.user.presentation.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CreateFriendRequest {

    private String code;

    public CreateFriendRequest(String code) {
        this.code = code;
    }

}
