package com.nexters.gaetteok.user.presentation;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


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
            .location("SEOUL")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.getUser(anyLong())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/users", id)
            .header("Authorization", "Bearer token")
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
            .location("SEOUL")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.updateNickname(anyLong(), anyString())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/users/nickname")
                .header("Authorization", "Bearer token")
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
            .location("SEOUL")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.updateProfile(anyLong(), any())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.multipart(HttpMethod.PATCH, "/api/users/profile-image")
                .file("file", "new-profile-image".getBytes())
                .header("Authorization", "Bearer token")
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
            .location("SEOUL")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.updateLocation(anyLong(), anyString())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/users/location")
                .header("Authorization", "Bearer token")
                .param("city", "SEOUL")
                .contentType("application/json"));

        // then
        resultActions.andExpect(status().isOk());
    }

}
