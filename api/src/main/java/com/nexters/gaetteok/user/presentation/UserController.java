package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.user.presentation.response.GetUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplication userApplication;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> getUser() {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        long userId = 1;
        return ResponseEntity.ok(GetUserResponse.of(userApplication.getUser(userId)));
    }

}
