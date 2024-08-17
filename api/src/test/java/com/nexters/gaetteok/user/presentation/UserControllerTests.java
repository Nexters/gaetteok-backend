package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTests extends AbstractControllerTests {

    @Test
    void getUser_correctId_success() throws Exception {
        // given
        long id = 1L;
        User user = User.builder()
            .id(id)
            .nickname("test")
            .code("123456")
            .profileUrl("https://profile-image.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.getUser(anyLong())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/users", id)
            .contentType("application/json"));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void patchUser_nickname_success() throws Exception {
        // given
        long id = 1L;
        User user = User.builder()
            .id(id)
            .nickname("test")
            .code("123456")
            .profileUrl("https://profile-image.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.updateNickname(anyLong(), anyString())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/users/nickname")
                .param("nickname", "test")
                .contentType("application/json"));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void patchUser_profileImage_success() throws Exception {
        // given
        long id = 1L;
        User user = User.builder()
            .id(id)
            .nickname("test")
            .code("123456")
            .profileUrl("https://profile-image.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.updateProfile(anyLong(), any())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.multipart(HttpMethod.PATCH, "/api/users/profile-image")
                .file("file", "new-profile-image".getBytes())
                .contentType(MediaType.MULTIPART_FORM_DATA));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void patchUser_location_success() throws Exception {
        // given
        long id = 1L;
        User user = User.builder()
            .id(id)
            .nickname("test")
            .code("123456")
            .location("Daegu")
            .profileUrl("https://profile-image.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.updateLocation(anyLong(), anyString())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/users/location")
                .param("city", "SEOUL")
                .contentType("application/json"));

        // then
        resultActions.andExpect(status().isOk());
    }

}
