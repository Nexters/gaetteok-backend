package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.domain.Reaction;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.walklog.application.ReactionApplication;
import com.nexters.gaetteok.walklog.presentation.request.CreateReactionRequest;
import com.nexters.gaetteok.walklog.presentation.response.CreateReactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/walk-logs/{walkLogId}/reaction")
@RequiredArgsConstructor
public class ReactionController implements ReactionSpecification {

    private final ReactionApplication reactionApplication;

    @PostMapping(
        value = "",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateReactionResponse> create(
        @PathVariable long walkLogId,
        @RequestBody CreateReactionRequest request,
        UserInfo userInfo) {
        log.info("[리액션 생성] userInfo={}, walkLogId={}, request={}", userInfo, walkLogId, request);
        Reaction reaction = reactionApplication.create(userInfo.getUserId(), walkLogId, request);
        log.info("[리액션 생성 완료] reaction={}", reaction);
        return ResponseEntity.ok(CreateReactionResponse.of(reaction));
    }

    @DeleteMapping(value = "/{reactionId}")
    public ResponseEntity<Void> delete(
        @PathVariable long walkLogId,
        @PathVariable long reactionId,
        UserInfo userInfo) {
        log.info("[리액션 삭제] walkLogId={}, reactionId={}", walkLogId, reactionId);
        reactionApplication.delete(userInfo.getUserId(), reactionId);
        log.info("[리액션 삭제 완료] reactionId={}", reactionId);
        return ResponseEntity.noContent().build();
    }

}
