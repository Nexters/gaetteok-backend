package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.walklog.application.CommentApplication;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateCommentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/walk-logs/{id}/comments")
@RequiredArgsConstructor
public class CommentController implements CommentSpecification {

    private final CommentApplication commentApplication;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCommentResponse> create(
        @PathVariable long id,
        @RequestBody CreateCommentRequest request,
        UserInfo userInfo) throws IOException {
        Comment comment = commentApplication.create(userInfo.getUserId(), id, request);
        return ResponseEntity.ok(CreateCommentResponse.of(comment));
    }
}
