package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.domain.UserPushNotification;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.user.presentation.response.GetUserPushNotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/users/push-notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserPushNotificationController implements UserPushNotificationSpecification {

    private final UserApplication userApplication;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserPushNotificationResponse> get(UserInfo userInfo) {
        int pushNotificationTime = userApplication.getPushNotificationTime(userInfo.getUserId());
        return ResponseEntity.ok(GetUserPushNotificationResponse.of(pushNotificationTime));
    }

    @PatchMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserPushNotificationResponse> update(
        @RequestParam("time") int pushNotificationTime,
        UserInfo userInfo) {
        log.info("[푸시 알림 시간 변경] userInfo={}, pushNotificationTime={}", userInfo, pushNotificationTime);
        UserPushNotification userPushNotification = userApplication.updatePushNotificationTime(userInfo.getUserId(), pushNotificationTime);
        log.info("[푸시 알림 시간 변경 완료] userPushNotification={}", userPushNotification);
        return ResponseEntity.ok(GetUserPushNotificationResponse.of(userPushNotification.getPushNotificationTime()));
    }

}
