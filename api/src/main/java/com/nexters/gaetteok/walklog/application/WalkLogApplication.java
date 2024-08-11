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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalkLogApplication {

    private final ImageUploader imageUploader;
    private final UserRepository userRepository;
    private final WalkLogRepository walkLogRepository;

    public WalkLog create(long userId, CreateWalkLogRequest request, MultipartFile photo) throws IOException {
        long startTime = System.currentTimeMillis();
        File file = imageUploader.uploadFiles(Collections.singletonList(photo), "/walk-log/images").get(0);
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

    public List<WalkLog> getMyList(long userId, long cursorId, int pageSize) {
        UserEntity me = userRepository.getById(userId);
        return walkLogRepository.getMyList(userId, cursorId, pageSize).stream()
                .map(walkLogEntity -> WalkLogMapper.toDomain(walkLogEntity, me))
                .toList();
    }

    public List<WalkLog> getList(long userId, long cursorId, int pageSize) {
        return walkLogRepository.getList(userId, cursorId, pageSize, LocalDate.now());
    }

}
