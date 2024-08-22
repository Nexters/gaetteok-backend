package com.nexters.gaetteok.common.presentation;

import com.nexters.gaetteok.common.application.AdminApplication;
import com.nexters.gaetteok.common.presentation.request.CreateCommentAdminRequest;
import com.nexters.gaetteok.common.presentation.request.CreateReactionAdminRequest;
import com.nexters.gaetteok.common.presentation.request.CreateWalkLogAdminRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController implements AdminSpecification {

    @Value("${pawpaw.admin.token}")
    private String adminToken;

    private final AdminApplication adminApplication;

    @PostMapping(value = "/walk-logs", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createWalkLogs(@RequestHeader(value = "Authorization", required = false) String token,
                                               @RequestPart CreateWalkLogAdminRequest request,
                                               @RequestPart(name = "photo") MultipartFile photo) throws IOException {
        if (!isValidToken(token)) {
            log.info("[관리자용 산책 기록 생성] 잘못된 관리자 토큰: {}", token);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        log.info("[관리자용 산책 기록 생성] request={}", request);
        adminApplication.createWalkLogs(request, photo);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createComments(@RequestHeader(value = "Authorization", required = false) String token,
                                               @RequestBody CreateCommentAdminRequest request) {
        if (!isValidToken(token)) {
            log.info("[관리자용 댓글 생성] 잘못된 관리자 토큰: {}", token);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        log.info("[관리자용 댓글 생성] request={}", request);
        adminApplication.createComments(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/reactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReactions(@RequestHeader(value = "Authorization", required = false) String token,
                                                @RequestBody CreateReactionAdminRequest request) {
        if (!isValidToken(token)) {
            log.info("[관리자용 리액션 생성] 잘못된 관리자 토큰: {}", token);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        log.info("[관리자용 리액션 생성] request={}", request);
        adminApplication.createReactions(request);
        return ResponseEntity.ok().build();
    }

    private boolean isValidToken(String token) {
        return StringUtils.hasText(token) && token.startsWith("Bearer ") && token.substring(7).equals(adminToken);
    }

}
