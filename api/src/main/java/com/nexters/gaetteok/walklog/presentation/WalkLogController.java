package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.walklog.application.WalkLogApplication;
import com.nexters.gaetteok.walklog.presentation.request.CreateWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateWalkLogResponse;
import com.nexters.gaetteok.walklog.presentation.response.GetWalkLogListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/walk-logs")
@RequiredArgsConstructor
public class WalkLogController {

    private final WalkLogApplication walkLogApplication;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateWalkLogResponse> create(@RequestPart CreateWalkLogRequest request,
                                                        @RequestPart(name = "photo") MultipartFile photo) throws IOException {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1;
        log.info("[산책 기록 생성] userId={}, request={}, hasPhoto={}", userId, request, !photo.isEmpty());
        WalkLog walkLog = walkLogApplication.create(userId, request, photo);
        log.info("[산책 기록 생성 완료] walkLog={}", walkLog);
        return ResponseEntity.ok(CreateWalkLogResponse.of(walkLog));
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetWalkLogListResponse> getMyList(@RequestParam(defaultValue = "0") long cursorId,
                                                            @RequestParam(defaultValue = "10") int pageSize) {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1;
        List<WalkLog> walkLogList = walkLogApplication.getListById(userId, cursorId, pageSize);
        return ResponseEntity.ok(GetWalkLogListResponse.of(walkLogList));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetWalkLogListResponse> getList(@RequestParam(required = false, defaultValue = "0") long userId,
                                                          @RequestParam(defaultValue = "0") long cursorId,
                                                          @RequestParam(defaultValue = "10") int pageSize) {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        List<WalkLog> walkLogList;
        if (userId > 0) {
            walkLogList = walkLogApplication.getListById(userId, cursorId, pageSize);
        } else {
            // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
            userId = 1;
            walkLogList = walkLogApplication.getList(userId, cursorId, pageSize);
        }
        return ResponseEntity.ok(GetWalkLogListResponse.of(walkLogList));
    }

}
