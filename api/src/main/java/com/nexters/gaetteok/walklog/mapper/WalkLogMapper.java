package com.nexters.gaetteok.walklog.mapper;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;

public class WalkLogMapper {

    public static WalkLog toDomain(WalkLogEntity entity, UserEntity writer) {
        return WalkLog.builder()
                .id(entity.getId())
                .photoUrl(entity.getPhotoUrl())
                .title(entity.getTitle())
                .content(entity.getContent())
                .walkTime(entity.getWalkTime())
                .writerNickname(writer.getNickname())
                .writerProfileUrl(writer.getProfileUrl())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
