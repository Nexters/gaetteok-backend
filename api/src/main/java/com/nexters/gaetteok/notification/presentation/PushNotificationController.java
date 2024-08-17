package com.nexters.gaetteok.notification.presentation;

import com.nexters.gaetteok.firebase.service.PushNotificationService;
import com.nexters.gaetteok.jwt.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/push")
@RequiredArgsConstructor
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    @PostMapping("/pick")
    public ResponseEntity<?> pushNotification(UserInfo userInfo) {
        pushNotificationService.sendPickNotification(userInfo.getUserId());

        return ResponseEntity.ok().build();
    }
}
