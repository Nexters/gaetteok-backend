package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.walklog.application.WalkLogApplication;
import com.nexters.gaetteok.walklog.presentation.request.CreateWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.request.PatchWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.request.ReportWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/api/walk-logs")
@RequiredArgsConstructor
public class WalkLogController implements WalkLogSpecification {

    private final WalkLogApplication walkLogApplication;
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateWalkLogResponse> create(
        @RequestPart CreateWalkLogRequest request,
        @RequestPart(name = "photo") MultipartFile photo,
        UserInfo userInfo) throws IOException {
        log.info(
            "[산책 기록 생성] userInfo={}, request={}, hasPhoto={}", userInfo, request, !photo.isEmpty());
        WalkLog walkLog = walkLogApplication.create(userInfo.getUserId(), request, photo);
        log.info("[산책 기록 생성 완료] walkLog={}", walkLog);
        return ResponseEntity.ok(CreateWalkLogResponse.of(walkLog));
    }

    @GetMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalkLogCalendarResponse> getCalendar(
        @RequestParam(required = false, defaultValue = "0") long userId,
        @RequestParam int year,
        @RequestParam int month,
        UserInfo userInfo
    ) {
        Map<String, WalkLog> walkLogList;
        if (userId > 0) {
            walkLogList = walkLogApplication.getCalendar(userId, year, month);
        } else {
            walkLogList = walkLogApplication.getCalendar(userInfo.getUserId(), year, month);
        }
        return ResponseEntity.ok(WalkLogCalendarResponse.of(walkLogList));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetWalkLogListResponse> getList(
        @RequestParam(required = false, defaultValue = "0") long userId,
        @RequestParam(required = false, defaultValue = "0") long cursorId,
        @RequestParam(required = false, defaultValue = "10") int pageSize,
        UserInfo userInfo) {
        List<WalkLog> walkLogList;
        if (userId > 0) {
            walkLogList = walkLogApplication.getListById(userId, cursorId, pageSize);
        } else {
            walkLogList = walkLogApplication.getList(userInfo.getUserId(), cursorId, pageSize);
        }
        return ResponseEntity.ok(GetWalkLogListResponse.of(walkLogList));
    }

    @GetMapping(
        value = "/monthly", produces = MediaType.APPLICATION_JSON_VALUE, params = {"userId", "year",
        "month"}
    )
    public ResponseEntity<GetWalkLogListGroupByMonthResponse> getListByUserIdAndMonth(
        @RequestParam long userId,
        @RequestParam int year,
        @RequestParam int month
    ) {
        List<WalkLog> walkLogList = walkLogApplication.getListByIdAndMonth(userId, year, month);
        WalkLog nextData = null;
        if (walkLogList.size() > 0) {
            WalkLog lastData = walkLogList.get(walkLogList.size() - 1);
            nextData = walkLogApplication.getNextData(lastData.getId());
        }
        return ResponseEntity.ok(
            GetWalkLogListGroupByMonthResponse.of(year, month, nextData, walkLogList));
    }

    @GetMapping(
        value = "/me", produces = MediaType.APPLICATION_JSON_VALUE, params = {"year", "month"}
    )
    public ResponseEntity<GetWalkLogListGroupByMonthResponse> getMyList(
        @RequestParam int year,
        @RequestParam int month,
        UserInfo userInfo) {
        List<WalkLog> walkLogList = walkLogApplication.getListByIdAndMonth(
            userInfo.getUserId(), year, month);
        WalkLog nextData = null;
        if (walkLogList.size() > 0) {
            WalkLog lastData = walkLogList.get(walkLogList.size() - 1);
            nextData = walkLogApplication.getNextData(lastData.getId());
        }
        return ResponseEntity.ok(
            GetWalkLogListGroupByMonthResponse.of(year, month, nextData, walkLogList));
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PatchWalkLogResponse> update(
        @PathVariable long id,
        @RequestPart PatchWalkLogRequest request,
        @RequestPart(name = "photo", required = false) MultipartFile photo,
        UserInfo userInfo) throws IOException {
        log.info(
            "[산책 기록 변경] userInfo={}, request={}, photo={}", userInfo, request, photo);
        WalkLog walkLog = walkLogApplication.update(id, userInfo.getUserId(), request, photo);
        log.info("[산책 기록 변경 완료] walkLog={}", walkLog);
        return ResponseEntity.ok(PatchWalkLogResponse.of(walkLog));
    }

    @PostMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportWalkLogResponse> reportUser(
        @RequestBody ReportWalkLogRequest request,
        UserInfo userInfo) {
        log.info("[유저 신고] userInfo={}, request={}", userInfo, request);
        return ResponseEntity.ok(
            ReportWalkLogResponse.of(atomicInteger.getAndIncrement(), LocalDateTime.now()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetWalkLogDetailResponse> getDetail(
        @PathVariable long id,
        UserInfo userInfo) {
        WalkLog walkLog = walkLogApplication.getOneById(id, userInfo.getUserId());

        return ResponseEntity.ok(
            GetWalkLogDetailResponse.of(walkLog, userInfo));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable long id,
        UserInfo userInfo) {
        walkLogApplication.delete(id, userInfo.getUserId());
        return ResponseEntity.noContent().build();
    }

}
