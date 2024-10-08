package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.domain.UserPushNotification;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.user.presentation.response.GetUserPushNotificationResponse;
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
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/users/push-notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserPushNotificationController implements UserPushNotificationSpecification {

    private final UserApplication userApplication;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserPushNotificationResponse> get(UserInfo userInfo) {
        UserPushNotification pushNotification = userApplication.getPushNotification(
            userInfo.getUserId());
        return ResponseEntity.ok(GetUserPushNotificationResponse.of(pushNotification));
    }

    @PatchMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserPushNotificationResponse> update(
        @RequestParam(value = "time") Integer pushNotificationTime,
        @RequestParam(value = "isOn") boolean isOn,
        UserInfo userInfo) {
        log.info(
            "[푸시 알림 시간, ON/OFF 변경] userInfo={}, pushNotificationTime={}", userInfo,
            pushNotificationTime
        );
        UserPushNotification userPushNotification = userApplication.updatePushNotificationTime(
            userInfo.getUserId(), pushNotificationTime, isOn);
        log.info("[푸시 알림 시간, ON/OFF 변경 완료] userPushNotification={}", userPushNotification);
        return ResponseEntity.ok(
            GetUserPushNotificationResponse.of(userPushNotification));
    }

}
