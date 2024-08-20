package com.nexters.gaetteok.walklog.application;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.persistence.entity.CommentEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.repository.CommentRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.walklog.mapper.CommentMapper;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.request.UpdateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentApplication {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment create(long writerId, long walkLogId, CreateCommentRequest request) {
        UserEntity userEntity = userRepository.findById(writerId)
            .orElseThrow(() -> new RuntimeException("User with id " + writerId + " not found"));
        CommentEntity commentEntity = CommentEntity.builder()
            .walkLogId(walkLogId)
            .content(request.getContent())
            .writerId(writerId)
            .build();

        CommentEntity savedEntity = commentRepository.save(commentEntity);

        return CommentMapper.toDomain(savedEntity, userEntity);
    }

    @Transactional
    public Comment update(long commentId, UpdateCommentRequest request) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
            .orElseThrow(() -> new RuntimeException("Comment with id " + commentId + " not found"));
        UserEntity userEntity = userRepository.findById(commentEntity.getWriterId())
            .orElseThrow(() -> new RuntimeException(
                "User with id " + commentEntity.getWriterId() + " not found"));

        CommentEntity updatedCommentEntity = CommentEntity.builder()
            .id(commentEntity.getId())
            .walkLogId(commentEntity.getWalkLogId())
            .content(request.getContent())
            .writerId(commentEntity.getWriterId())
            .build();

        CommentEntity savedEntity = commentRepository.save(updatedCommentEntity);

        return CommentMapper.toDomain(savedEntity, userEntity);
    }

    @Transactional
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }

}
