package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.common.auth.UserInfo;
import com.nexters.gaetteok.user.application.UserApplication;
import com.nexters.gaetteok.user.presentation.response.GetUserResponse;
import com.nexters.gaetteok.user.presentation.response.UpdateUserLocationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplication userApplication;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> getUser(UserInfo userInfo) {
        return ResponseEntity.ok(GetUserResponse.of(userApplication.getUser(userInfo.getUserId())));
    }

    @PatchMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateUserLocationResponse> updateLocation(
        @RequestParam String location,
        UserInfo userInfo
    ) {
        return ResponseEntity.ok(
            UpdateUserLocationResponse.of(userApplication.updateUser(userInfo.getUserId(), location)));
    }

}
