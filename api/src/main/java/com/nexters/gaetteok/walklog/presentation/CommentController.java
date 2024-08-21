package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.walklog.application.CommentApplication;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.request.UpdateCommentRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateCommentResponse;
import com.nexters.gaetteok.walklog.presentation.response.UpdateCommentResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/walk-logs")
@RequiredArgsConstructor
public class CommentController implements CommentSpecification {

    private final CommentApplication commentApplication;

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
}
