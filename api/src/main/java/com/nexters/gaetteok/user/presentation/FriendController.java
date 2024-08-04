package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.user.application.FriendApplication;
import com.nexters.gaetteok.user.presentation.request.CreateFriendRequest;
import com.nexters.gaetteok.user.presentation.response.CreateFriendResponse;
import com.nexters.gaetteok.user.presentation.response.GetFriendListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendApplication friendApplication;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateFriendResponse> create(@RequestBody CreateFriendRequest request) {
        // TODO 토큰에서 꺼내온 사용자 고유값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1L;
        return ResponseEntity.ok(CreateFriendResponse.of(friendApplication.create(userId, request.getCode())));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetFriendListResponse> getList() {
        // TODO 토큰에서 꺼내온 사용자 고유값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1L;
        return ResponseEntity.ok(GetFriendListResponse.of(friendApplication.getMyFriendList(userId)));
    }

}
