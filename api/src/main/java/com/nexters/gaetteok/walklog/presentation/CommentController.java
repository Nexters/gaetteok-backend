package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.walklog.application.CommentApplication;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateCommentResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/walk-logs/{id}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentApplication commentApplication;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCommentResponse> create(
        @PathVariable long id,
        @RequestBody CreateCommentRequest request) throws IOException {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1;
        Comment comment = commentApplication.create(userId, id, request);
        return ResponseEntity.ok(CreateCommentResponse.of(comment));
    }
}
