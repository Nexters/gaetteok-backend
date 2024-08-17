package com.nexters.gaetteok.walklog.mapper;

import com.nexters.gaetteok.domain.Reaction;
import com.nexters.gaetteok.persistence.entity.ReactionEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;

public class ReactionMapper {

    public static Reaction toDomain(ReactionEntity entity, UserEntity userEntity) {
        return Reaction.builder()
            .id(entity.getId())
            .writerNickname(userEntity.getNickname())
            .writerProfileImageUrl(userEntity.getProfileUrl())
            .reactionType(entity.getReactionType())
            .walkLogId(entity.getWalkLogId())
            .createdAt(entity.getCreatedAt())
            .build();
    }

}
