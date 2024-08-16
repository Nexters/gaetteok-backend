package com.nexters.gaetteok.walklog.application;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.image.model.File;
import com.nexters.gaetteok.image.service.ImageUploader;
import com.nexters.gaetteok.persistence.entity.CommentEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import com.nexters.gaetteok.persistence.repository.CommentRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.persistence.repository.WalkLogRepository;
import com.nexters.gaetteok.walklog.mapper.CommentMapper;
import com.nexters.gaetteok.walklog.mapper.WalkLogMapper;
import com.nexters.gaetteok.walklog.presentation.request.CreateWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.request.PatchWalkLogRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalkLogApplication {

    private final ImageUploader imageUploader;
    private final UserRepository userRepository;
    private final WalkLogRepository walkLogRepository;
    private final CommentRepository commentRepository;

    private String getKey(WalkLog walkLogEntity) {
        return
            walkLogEntity.getCreatedAt().getYear() + "-" +
                walkLogEntity.getCreatedAt().getMonthValue() + "-" +
                walkLogEntity.getCreatedAt().getDayOfMonth();
    }

    public WalkLog create(long userId, CreateWalkLogRequest request, MultipartFile photo)
        throws IOException {
        long startTime = System.currentTimeMillis();
        File file = imageUploader.uploadFiles(Collections.singletonList(photo), "walk-log/images")
            .get(0);
        log.info("파일 업로드 완료 (소요 시간: {})", System.currentTimeMillis() - startTime);

        UserEntity me = userRepository.getById(userId);
        WalkLogEntity walkLog = walkLogRepository.save(WalkLogEntity.builder()
            .photoUrl(file.getUploadFileUrl())
            .title(request.getTitle())
            .content(request.getContent())
            .walkTime(request.getWalkTime())
            .userId(userId)
            .build());
        return WalkLogMapper.toDomain(walkLog, me);
    }

    public Map<String, WalkLog> getCalendar(long userId, int year, int month) {
        UserEntity me = userRepository.getById(userId);
        List<WalkLog> walkLogs = walkLogRepository.getCalendar(userId, year, month)
            .stream()
            .map(walkLogEntity -> WalkLogMapper.toDomain(walkLogEntity, me))
            .sorted(Comparator.comparing(WalkLog::getCreatedAt))
            .toList();

        Map<String, WalkLog> calendar = new LinkedHashMap<>();
        walkLogs.forEach(
            walkLogEntity -> calendar.put(
                getKey(walkLogEntity),
                walkLogEntity
            )
        );

        return calendar;
    }

    // 이건 나중에 지워도 되는건가?
    public List<WalkLog> getList(long userId, long cursorId, int pageSize) {
        return walkLogRepository.getList(userId, cursorId, pageSize);
    }

    public List<WalkLog> getListById(long userId, int year, int month) {
        UserEntity me = userRepository.getById(userId);
        List<WalkLog> walkLogs = walkLogRepository.getMyList(userId, year, month).stream()
            .map(walkLogEntity -> WalkLogMapper.toDomain(walkLogEntity, me))
            .toList();

        List<Long> walkLogIds = walkLogs.stream()
            .map(WalkLog::getId)
            .toList();

        List<Long> writerIds = commentRepository.findByWalkLogIdIn(walkLogIds)
            .stream().map(
                CommentEntity::getWriterId
            )
            .toList();

        Map<Long, UserEntity> users = new HashMap<>();
        userRepository.findAllById(writerIds)
            .forEach(
                user -> users.put(user.getId(), user)
            );

        Map<Long, List<Comment>> comments = commentRepository.findByWalkLogIdIn(walkLogIds)
            .stream().map(commentEntity -> CommentMapper.toDomain(commentEntity,
                users.get(commentEntity.getWriterId())
            ))
            .collect(Collectors.groupingBy(Comment::getWalkLogId));

        walkLogs.forEach(
            walkLog -> walkLog.setComments(comments.get(walkLog.getId()))
        );

        return walkLogs;
    }

    public WalkLog update(long id, long userId, PatchWalkLogRequest request, MultipartFile photo)
        throws IOException {
        UserEntity me = userRepository.getById(userId);
        WalkLogEntity walkLog = walkLogRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("walkLog does not exist"));
        File file = null;
        if (photo != null && !photo.isEmpty()) {
            long startTime = System.currentTimeMillis();
            file = imageUploader.uploadFiles(Collections.singletonList(photo), "walk-log/images")
                .get(0);
            log.info("파일 업로드 완료 (소요 시간: {})", System.currentTimeMillis() - startTime);
        }

        walkLogRepository.save(WalkLogEntity.builder()
            .id(id)
            .photoUrl(file != null ? file.getUploadFileUrl() : walkLog.getPhotoUrl())
            .title(request.getTitle() != null ? request.getTitle() : walkLog.getTitle())
            .content(request.getContent() != null ? request.getContent() : walkLog.getContent())
            .userId(userId)
            .walkTime(walkLog.getWalkTime())
            .build()
        );

        return WalkLogMapper.toDomain(walkLog, me);
    }

    public WalkLog getNextData(long walkLogId) {
        WalkLogEntity entity = walkLogRepository.getMaxIdLessThan(walkLogId);
        if (entity == null) {
            return null;
        }
        UserEntity me = userRepository.getById(entity.getUserId());
        return WalkLogMapper.toDomain(entity, me);
    }

}
