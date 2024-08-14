package com.nexters.gaetteok.walklog.application;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.image.model.File;
import com.nexters.gaetteok.image.service.ImageUploader;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.persistence.repository.WalkLogRepository;
import com.nexters.gaetteok.walklog.mapper.WalkLogMapper;
import com.nexters.gaetteok.walklog.presentation.request.CreateWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.request.PatchWalkLogRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalkLogApplication {

    private final ImageUploader imageUploader;
    private final UserRepository userRepository;
    private final WalkLogRepository walkLogRepository;

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

    public List<WalkLog> getList(long userId, long cursorId, int pageSize) {
        return walkLogRepository.getList(userId, cursorId, pageSize);
    }

    public List<WalkLog> getListById(long userId, long cursorId, int pageSize) {
        UserEntity me = userRepository.getById(userId);
        return walkLogRepository.getMyList(userId, cursorId, pageSize).stream()
            .map(walkLogEntity -> WalkLogMapper.toDomain(walkLogEntity, me))
            .toList();
    }

    public WalkLog update(long id, long userId, PatchWalkLogRequest request, MultipartFile photo)
        throws IOException {
        UserEntity me = userRepository.getById(userId);
        WalkLogEntity walkLog = walkLogRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("walkLog does not exist"));
        File file = null;
        if (!photo.isEmpty()) {
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

}
