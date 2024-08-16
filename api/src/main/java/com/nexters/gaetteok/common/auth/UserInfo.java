package com.nexters.gaetteok.common.auth;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserInfo {

    private long userId;

    public UserInfo(long userId) {
        this.userId = userId;
    }

}
