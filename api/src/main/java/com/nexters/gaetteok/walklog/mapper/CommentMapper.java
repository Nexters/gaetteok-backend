package com.nexters.gaetteok.walklog.mapper;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.persistence.entity.CommentEntity;

public class CommentMapper {

    public static Comment toDomain(CommentEntity commentEntity) {
        return Comment.builder()
            .id(commentEntity.getId())
            .writerId(commentEntity.getWriterId())
            .content(commentEntity.getContent())
            .walkLogId(commentEntity.getId())
            .createdAt(commentEntity.getCreatedAt())
            .build();
    }

}
