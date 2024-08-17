package com.nexters.gaetteok.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserInfo {

    private long userId;

    @Builder
    public UserInfo(long userId) {
        this.userId = userId;
    }

}
