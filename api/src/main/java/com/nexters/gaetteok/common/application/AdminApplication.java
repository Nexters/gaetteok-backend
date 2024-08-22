package com.nexters.gaetteok.common.application;

import com.nexters.gaetteok.common.presentation.request.CreateCommentAdminRequest;
import com.nexters.gaetteok.common.presentation.request.CreateReactionAdminRequest;
import com.nexters.gaetteok.common.presentation.request.CreateWalkLogAdminRequest;
import com.nexters.gaetteok.image.model.File;
import com.nexters.gaetteok.image.service.ImageUploader;
import com.nexters.gaetteok.persistence.entity.CommentEntity;
import com.nexters.gaetteok.persistence.entity.ReactionEntity;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import com.nexters.gaetteok.persistence.repository.CommentRepository;
import com.nexters.gaetteok.persistence.repository.ReactionRepository;
import com.nexters.gaetteok.persistence.repository.WalkLogRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminApplication {

    private final ImageUploader imageUploader;

    private final EntityManager entityManager;
    private final WalkLogRepository walkLogRepository;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;

    @Transactional
    public void createWalkLogs(CreateWalkLogAdminRequest request, MultipartFile photo) throws IOException {
        File file = imageUploader.uploadFiles(Collections.singletonList(photo), "admin/walk-log/images").getFirst();

        WalkLogEntity entity = walkLogRepository.save(WalkLogEntity.builder()
            .photoUrl(file.getUploadFileUrl())
            .title(request.getTitle())
            .content(request.getContent())
            .walkTime(request.getWalkTime())
            .userId(request.getUserId())
            .build());

        // JPA가 직접 관리하는 createdAt 필드를 수정하기 위해 쿼리를 직접 실행
        entityManager.createQuery("UPDATE WalkLogEntity w SET w.createdAt = :createdAt WHERE w.id = :id")
            .setParameter("createdAt", request.getCreatedAt())
            .setParameter("id", entity.getId())
            .executeUpdate();
    }

    @Transactional
    public void createComments(CreateCommentAdminRequest request) {
        CommentEntity entity = commentRepository.save(CommentEntity.builder()
            .walkLogId(request.getWalkLogId())
            .content(request.getContent())
            .userId(request.getUserId())
            .build());

        // JPA가 직접 관리하는 createdAt 필드를 수정하기 위해 쿼리를 직접 실행
        entityManager.createQuery("UPDATE CommentEntity c SET c.createdAt = :createdAt WHERE c.id = :id")
            .setParameter("createdAt", request.getCreatedAt())
            .setParameter("id", entity.getId())
            .executeUpdate();
    }

    @Transactional
    public void createReactions(CreateReactionAdminRequest request) {
        ReactionEntity entity = reactionRepository.save(ReactionEntity.builder()
            .walkLogId(request.getWalkLogId())
            .userId(request.getUserId())
            .reactionType(request.getReactionType())
            .build());

        // JPA가 직접 관리하는 createdAt 필드를 수정하기 위해 쿼리를 직접 실행
        entityManager.createQuery("UPDATE ReactionEntity r SET r.createdAt = :createdAt WHERE r.id = :id")
            .setParameter("createdAt", request.getCreatedAt())
            .setParameter("id", entity.getId())
            .executeUpdate();
    }

}
