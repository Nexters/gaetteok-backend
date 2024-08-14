package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.domain.Friend;
import com.nexters.gaetteok.domain.FriendWalkStatus;
import com.nexters.gaetteok.user.application.FriendApplication;
import com.nexters.gaetteok.user.presentation.request.CreateFriendRequest;
import com.nexters.gaetteok.user.presentation.response.CreateFriendResponse;
import com.nexters.gaetteok.user.presentation.response.GetFriendListResponse;
import com.nexters.gaetteok.user.presentation.response.GetFriendWalkStatusListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendApplication friendApplication;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateFriendResponse> create(@RequestBody CreateFriendRequest request) {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1L;
        log.info("[친구 관계 생성] userId={}, request={}", userId, request);
        Friend friend = friendApplication.create(userId, request.getCode());
        log.info("[친구 관계 생성 완료] friend={}", friend);
        return ResponseEntity.ok(CreateFriendResponse.of(friend));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetFriendListResponse> getList() {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1L;
        List<Friend> friendList = friendApplication.getMyFriendList(userId);
        return ResponseEntity.ok(GetFriendListResponse.of(friendList));
    }

    @GetMapping(value = "/walk-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetFriendWalkStatusListResponse> getWalkStatusList() {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1L;
        List<FriendWalkStatus> friendWalkStatusList = friendApplication.getWalkStatusList(userId);
        return ResponseEntity.ok(new GetFriendWalkStatusListResponse(friendWalkStatusList));
    }

}
