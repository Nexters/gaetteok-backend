package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.user.presentation.response.GetUserPushNotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserPushNotificationController {

    private final UserApplication userApplication;

    @GetMapping(value = "/{userId}/push")
    public GetUserPushNotificationResponse get(@PathVariable long userId) {

        long pushNotificationTime = userApplication.getPushNotificationTime(userId);

        return GetUserPushNotificationResponse.of(pushNotificationTime);
    }

    @PatchMapping(value = "/{userId}/push")
    public ResponseEntity<?> update(
        @PathVariable long userId,
        @RequestParam("time") long pushNotificationTime) {
        userApplication.updatePushNotificationTime(userId, pushNotificationTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
