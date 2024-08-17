package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.user.presentation.request.ReportUserRequest;
import com.nexters.gaetteok.user.presentation.response.GetUserResponse;
import com.nexters.gaetteok.user.presentation.response.ReportUserResponse;
import com.nexters.gaetteok.user.presentation.response.UpdateUserLocationResponse;
import com.nexters.gaetteok.weather.enums.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserSpecification {

    private final UserApplication userApplication;
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

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

    @PatchMapping(
        value = "/profile-image",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetUserResponse> updateProfile(
        @RequestParam(name = "file") MultipartFile profileImage,
        UserInfo userInfo
    ) throws IOException {
        log.info("[유저 프로필 수정] userInfo={}, profile={}", userInfo, profileImage);
        User user = userApplication.updateProfile(userInfo.getUserId(), profileImage);
        log.info("[유저 프로필 수정 완료] user={}", user);
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

    @PostMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportUserResponse> reportUser(@RequestBody ReportUserRequest request,
                                                         UserInfo userInfo) {
        log.info("[유저 신고] userInfo={}, request={}", userInfo, request);
        return ResponseEntity.ok(ReportUserResponse.of(atomicInteger.getAndIncrement(), LocalDateTime.now()));
    }

}
