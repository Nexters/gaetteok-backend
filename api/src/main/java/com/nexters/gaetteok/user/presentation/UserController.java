package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.common.auth.UserInfo;
import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.user.presentation.response.GetUserResponse;
import com.nexters.gaetteok.user.presentation.response.UpdateUserLocationResponse;
import com.nexters.gaetteok.weather.enums.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserSpecification {

    private final UserApplication userApplication;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> getUser(UserInfo userInfo) {
        return ResponseEntity.ok(GetUserResponse.of(userApplication.getUser(userInfo.getUserId())));
    }

    @PatchMapping(value = "/nickname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> updateNickname(
        @RequestParam String nickname,
        UserInfo userInfo
    ) {
        log.info("[유저 닉네임 수정] userInfo={}, nickname={}", userInfo, nickname);
        User user = userApplication.updateNickname(userInfo.getUserId(), nickname);
        log.info("[유저 닉네임 수정 완료] user={}", user);
        return ResponseEntity.ok(GetUserResponse.of(user));
    }

    @PatchMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateUserLocationResponse> updateLocation(
        @RequestParam City city,
        UserInfo userInfo
    ) {
        log.info("[유저 위치 수정] userInfo={}, location={}", userInfo, city);
        User user = userApplication.updateLocation(userInfo.getUserId(), city.name());
        log.info("[유저 위치 수정 완료] user={}", user);
        return ResponseEntity.ok(UpdateUserLocationResponse.of(user));
    }

}
