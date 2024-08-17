package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.AuthProvider;
import com.nexters.gaetteok.user.presentation.request.SignupRequest;
import com.nexters.gaetteok.weather.enums.City;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTests extends AbstractControllerTests {

    @Test
    void signup_correctData_success() throws Exception {
        // given
        SignupRequest request = SignupRequest.builder()
            .oauthIdentifier("kakao_token")
            .deviceToken("device_token")
            .nickname("닉네임")
            .profileUrl("https://profile.com")
            .city(City.SEJONG)
            .provider(AuthProvider.KAKAO)
            .build();
        given(authApplication.signup(any())).willReturn("token");

        // when
        ResultActions resultActions = mockMvc.perform(post("/api/auth/signup")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(request)));

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void login_existUser_success() throws Exception {
        // given
        String oauthIdentifier = "kakao_token";
        String deviceToken = "device_token";
        String userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NH0.1odevtEc20SuXWzNADHdiRvrRGCOUbGKme4n_JkJ5C8";
        given(authApplication.getUserToken(anyString(), anyString())).willReturn(Optional.of(userToken));

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/auth/login")
            .param("oauthIdentifier", oauthIdentifier)
            .param("deviceToken", deviceToken));

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").exists());
    }

}
