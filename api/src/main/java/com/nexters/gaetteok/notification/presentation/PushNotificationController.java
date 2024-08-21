package com.nexters.gaetteok.notification.presentation;

import com.nexters.gaetteok.firebase.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/push")
@RequiredArgsConstructor
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    @PostMapping("/users/{targetUserId}")
    public ResponseEntity<?> pushNotification(@PathVariable long targetUserId)
        throws BadRequestException {
        pushNotificationService.sendPickNotification(targetUserId);

        return ResponseEntity.ok().build();
    }
}
