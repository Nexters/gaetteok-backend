package com.nexters.gaetteok.walklog.application;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.domain.Reaction;
import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.image.model.File;
import com.nexters.gaetteok.image.service.ImageUploader;
import com.nexters.gaetteok.persistence.entity.CommentEntity;
import com.nexters.gaetteok.persistence.entity.ReactionEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import com.nexters.gaetteok.persistence.repository.CommentRepository;
import com.nexters.gaetteok.persistence.repository.ReactionRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.persistence.repository.WalkLogRepository;
import com.nexters.gaetteok.walklog.mapper.CommentMapper;
import com.nexters.gaetteok.walklog.mapper.ReactionMapper;
import com.nexters.gaetteok.walklog.mapper.WalkLogMapper;
import com.nexters.gaetteok.walklog.presentation.request.CreateWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.request.PatchWalkLogRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalkLogApplication {

    private final ImageUploader imageUploader;
    private final UserRepository userRepository;
    private final WalkLogRepository walkLogRepository;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;

    private String getKey(WalkLog walkLogEntity) {
        return
            walkLogEntity.getCreatedAt().getYear() + "-" +
                walkLogEntity.getCreatedAt().getMonthValue() + "-" +
                walkLogEntity.getCreatedAt().getDayOfMonth();
    }

    private void setComments(List<WalkLog> walkLogs, List<Long> walkLogIds) {
        List<Long> writerIds = commentRepository.findByWalkLogIdIn(walkLogIds)
            .stream().map(
                CommentEntity::getUserId
            )
            .toList();

        Map<Long, UserEntity> userEntityMap = userRepository.findAllById(writerIds)
            .stream().collect(
                Collectors.toMap(UserEntity::getId, user -> user)
            );

        Map<Long, List<Comment>> commentMap = commentRepository.findByWalkLogIdIn(walkLogIds)
            .stream().map(commentEntity -> CommentMapper.toDomain(
                commentEntity,
                userEntityMap.get(commentEntity.getUserId())
            ))
            .collect(Collectors.groupingBy(Comment::getWalkLogId));

        walkLogs.forEach(
            walkLog -> walkLog.setComments(commentMap.get(walkLog.getId()))
        );
    }

    private void setReactions(List<WalkLog> walkLogs, List<Long> walkLogIds) {
        List<Long> writerIds = reactionRepository.findByWalkLogIdIn(walkLogIds)
            .stream().map(
                ReactionEntity::getUserId
            )
            .toList();

        Map<Long, UserEntity> userEntityMap = userRepository.findAllById(writerIds)
            .stream().collect(
                Collectors.toMap(UserEntity::getId, user -> user)
            );

        Map<Long, List<Reaction>> reactionMap = reactionRepository.findByWalkLogIdIn(walkLogIds)
            .stream().map(reactionEntity -> ReactionMapper.toDomain(
                reactionEntity,
                userEntityMap.get(reactionEntity.getUserId())
            ))
            .collect(Collectors.groupingBy(Reaction::getWalkLogId));

        walkLogs.forEach(
            walkLog -> walkLog.setReactions(reactionMap.get(walkLog.getId()))
        );
    }

    @Transactional
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
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build());
        return WalkLogMapper.toDomain(walkLog, me);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public List<WalkLog> getList(long userId, long cursorId, int pageSize) {
        return walkLogRepository.getListOfMeAndMyFriend(userId, cursorId, pageSize);
    }

    @Transactional(readOnly = true)
    public List<WalkLog> getListById(long userId, long cursorId, int pageSize) {
        return walkLogRepository.getListOnlyMe(userId, cursorId, pageSize);
    }

    @Transactional(readOnly = true)
    public WalkLog getOneById(long walkLogId) {
        WalkLogEntity walkLogEntity = walkLogRepository.getById(walkLogId);
        UserEntity userEntity = userRepository.getById(walkLogEntity.getUserId());
        WalkLog walkLog = WalkLogMapper.toDomain(walkLogEntity, userEntity);

        List<Comment> comments = commentRepository.findByWalkLogIdInWithUser(walkLogId);
        List<Reaction> reactions = reactionRepository.findByWalkLogIdInWithUser(walkLogId);

        walkLog.setComments(comments);
        walkLog.setReactions(reactions);

        return walkLog;
    }

    @Transactional(readOnly = true)
    public List<WalkLog> getListByIdAndMonth(long userId, int year, int month) {
        UserEntity me = userRepository.getById(userId);
        List<WalkLog> walkLogs = walkLogRepository.getListByUserIdAndMonth(userId, year, month)
            .stream()
            .map(walkLogEntity -> WalkLogMapper.toDomain(walkLogEntity, me))
            .toList();

        List<Long> walkLogIds = walkLogs.stream()
            .map(WalkLog::getId)
            .toList();

        setComments(walkLogs, walkLogIds);
        setReactions(walkLogs, walkLogIds);

        return walkLogs;
    }

    public WalkLog getNextData(long walkLogId) {
        WalkLogEntity entity = walkLogRepository.getMaxIdLessThan(walkLogId);
        if (entity == null) {
            return null;
        }
        UserEntity me = userRepository.getById(entity.getUserId());
        return WalkLogMapper.toDomain(entity, me);
    }

    @Transactional
    public WalkLog update(long id, long userId, PatchWalkLogRequest request, MultipartFile photo)
        throws IOException {
        UserEntity me = userRepository.getById(userId);
        WalkLogEntity walkLog = walkLogRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("walkLog does not exist"));
        File file = null;
        if (photo != null && !photo.isEmpty()) {
            long startTime = System.currentTimeMillis();
            file = imageUploader.uploadFiles(Collections.singletonList(photo), "walk-logs")
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
            .updatedAt(LocalDateTime.now())
            .build()
        );

        return WalkLogMapper.toDomain(walkLog, me);
    }

    public boolean isTodayWalkLogExists(long userId) {
        return walkLogRepository.isTodayWalkLogExists(userId, LocalDate.now());
    }

    public void delete(long id, long userId) {
        WalkLogEntity walkLog = walkLogRepository.getById(id);
        if (walkLog.getUserId() != userId) {
            log.warn("자신이 작성한 리액션만 삭제할 수 있습니다. 제거 요청자={}, 작성자={}", userId, walkLog.getUserId());
            throw new IllegalArgumentException("자신이 작성한 리액션만 삭제할 수 있습니다.");
        }
        walkLogRepository.delete(walkLog);
    }

}
