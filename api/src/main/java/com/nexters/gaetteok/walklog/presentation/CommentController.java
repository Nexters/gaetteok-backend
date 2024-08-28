package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.walklog.application.CommentApplication;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.request.ReportCommentRequest;
import com.nexters.gaetteok.walklog.presentation.request.UpdateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateCommentResponse;
import com.nexters.gaetteok.walklog.presentation.response.ReportCommentResponse;
import com.nexters.gaetteok.walklog.presentation.response.UpdateCommentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/api/walk-logs")
@RequiredArgsConstructor
public class CommentController implements CommentSpecification {

    private final CommentApplication commentApplication;
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    @PostMapping(value = "/{id}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCommentResponse> create(
        @PathVariable long id,
        @RequestBody CreateCommentRequest request,
        UserInfo userInfo) throws IOException {
        Comment comment = commentApplication.create(userInfo.getUserId(), id, request);
        return ResponseEntity.ok(CreateCommentResponse.of(comment));
    }

    @PatchMapping(value = "/comments/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateCommentResponse> update(
        @PathVariable long id,
        @RequestBody UpdateCommentRequest request) {
        Comment comment = commentApplication.update(id, request);
        return ResponseEntity.ok(UpdateCommentResponse.of(comment));
    }

    @DeleteMapping(value = "/comments/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable long id,
        UserInfo userInfo) throws IOException {
        commentApplication.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/comments/report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportCommentResponse> reportComment(@RequestBody ReportCommentRequest request,
                                                               UserInfo userInfo) {
        log.info("[댓글 신고] userInfo={}, request={}", userInfo, request);
        return ResponseEntity.ok(ReportCommentResponse.of(atomicInteger.getAndIncrement(), request.getReason(), LocalDateTime.now()));
    }

}
