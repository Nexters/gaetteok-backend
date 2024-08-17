package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.user.application.AuthApplication;
import com.nexters.gaetteok.user.presentation.request.SignupRequest;
import com.nexters.gaetteok.user.presentation.response.LoginResponse;
import com.nexters.gaetteok.user.presentation.response.SignupResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthSpecification {

    private final AuthApplication authApplication;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request) {
        log.info("[회원가입] request={}", request);
        String userToken = authApplication.signup(request);
        return ResponseEntity.ok(new SignupResponse(userToken));
    }

    @GetMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String oauthIdentifier) {
        Optional<String> userTokenOptional = authApplication.getUserToken(oauthIdentifier);
        return userTokenOptional
            .map(s -> ResponseEntity.ok(new LoginResponse(s))).orElseGet(() -> ResponseEntity.noContent().build());
    }

}
