package com.nexters.gaetteok.user.mapper;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.weather.enums.City;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return User.builder()
            .id(entity.getId())
            .nickname(entity.getNickname())
            .profileUrl(entity.getProfileUrl())
            .location(entity.getLocation().name())
            .code(entity.getCode())
            .createdAt(entity.getCreatedAt())
            .build();
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .profileUrl(user.getProfileUrl())
            .city(City.valueOf(user.getLocation()))
            .code(user.getCode())
            .createdAt(user.getCreatedAt())
            .build();
    }

}
