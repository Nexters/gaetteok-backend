package com.nexters.gaetteok.user.presentation;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.UserPushNotification;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class UserPushNotificationControllerTests extends AbstractControllerTests {

    @Test
    void get_correctUserInfo_success() throws Exception {
        // given
        int pushNotificationTime = 600;
        UserPushNotification userPushNotification = new UserPushNotification(
            1, 1, pushNotificationTime, false);
        given(userApplication.getPushNotification(anyLong())).willReturn(userPushNotification);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/users/push-notification")
            .header("Authorization", "Bearer token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("pushNotificationTime", String.valueOf(pushNotificationTime)));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void update_correctData_success() throws Exception {
        // given
        int pushNotificationTime = 600;
        UserPushNotification userPushNotification = UserPushNotification.builder()
            .id(1L)
            .userId(1L)
            .pushNotificationTime(pushNotificationTime)
            .build();
        given(userApplication.updatePushNotificationTime(anyLong(), anyInt(), anyBoolean()))
            .willReturn(userPushNotification);

        // when
        ResultActions resultActions = mockMvc.perform(patch("/api/users/push-notification")
            .header("Authorization", "Bearer token")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("time", String.valueOf(pushNotificationTime)));

        // then
        resultActions.andExpect(status().isOk());
    }

}
