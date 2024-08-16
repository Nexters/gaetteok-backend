package com.nexters.gaetteok.common.auth;

import lombok.Getter;

@Getter
public class UserInfo {

    private long userId;

    public UserInfo(long userId) {
        this.userId = userId;
    }

}
