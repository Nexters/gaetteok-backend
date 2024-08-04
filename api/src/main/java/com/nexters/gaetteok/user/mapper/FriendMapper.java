package com.nexters.gaetteok.user.mapper;

import com.nexters.gaetteok.domain.Friend;
import com.nexters.gaetteok.persistence.entity.FriendEntity;

public class FriendMapper {

    public static Friend toDomain(FriendEntity entity) {
        return Friend.builder()
                .id(entity.getId())
                .me(UserMapper.toDomain(entity.getMe()))
                .friend(UserMapper.toDomain(entity.getFriend()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
