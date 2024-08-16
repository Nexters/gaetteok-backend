package com.nexters.gaetteok.walklog.mapper;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.persistence.entity.CommentEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;

public class CommentMapper {

    public static Comment toDomain(CommentEntity commentEntity, UserEntity userEntity) {
        return Comment.builder()
            .id(commentEntity.getId())
            .writerNickname(userEntity.getNickname())
            .writerProfileImageUrl(userEntity.getProfileUrl())
            .content(commentEntity.getContent())
            .walkLogId(commentEntity.getWalkLogId())
            .createdAt(commentEntity.getCreatedAt())
            .build();
    }

}
