package com.nexters.gaetteok.user.mapper;

import com.nexters.gaetteok.domain.Friend;
import com.nexters.gaetteok.persistence.entity.FriendEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;

public class FriendMapper {

    public static Friend toDomain(FriendEntity entity, UserEntity me, UserEntity friend) {
        return Friend.builder()
                .id(entity.getId())
                .me(UserMapper.toDomain(me))
                .friend(UserMapper.toDomain(friend))
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
