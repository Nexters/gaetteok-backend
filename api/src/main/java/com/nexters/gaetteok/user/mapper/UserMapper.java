package com.nexters.gaetteok.user.mapper;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.persistence.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .profileUrl(entity.getProfileUrl())
                .code(entity.getCode())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
