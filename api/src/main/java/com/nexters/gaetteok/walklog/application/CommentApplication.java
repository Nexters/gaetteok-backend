package com.nexters.gaetteok.walklog.application;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.persistence.entity.CommentEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.repository.CommentRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.walklog.mapper.CommentMapper;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
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

}
