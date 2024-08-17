package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.Friend;
import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.user.presentation.request.CreateFriendRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class FriendControllerTests extends AbstractControllerTests {

    @Test
    void create_correctIdAndCode_success() throws Exception {
        // given
        User meUser = User.builder()
            .id(1L)
            .nickname("me")
            .code("1a2b3c")
            .profileUrl("https://profile-image.jpg")
            .build();
        User friendUser = User.builder()
            .id(2L)
            .nickname("friend")
            .code("4d5e6f")
            .profileUrl("https://profile-image.jpg")
            .build();
        Friend friend = Friend.builder()
            .id(1L)
            .me(meUser)
            .friend(friendUser)
            .build();
        given(friendApplication.create(anyLong(), anyString())).willReturn(friend);

        CreateFriendRequest request = new CreateFriendRequest(friendUser.getCode());

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/friends")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getList_correctId_success() throws Exception {
        // given
        User meUser = User.builder()
            .id(1L)
            .nickname("me")
            .code("1a2b3c")
            .profileUrl("https://profile-image.jpg")
            .build();
        User friendUser = User.builder()
            .id(2L)
            .nickname("friend")
            .code("4d5e6f")
            .profileUrl("https://profile-image.jpg")
            .build();
        Friend friend = Friend.builder()
            .id(1L)
            .me(meUser)
            .friend(friendUser)
            .build();
        given(friendApplication.getMyFriendList(anyLong())).willReturn(List.of(friend));

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/friends")
                .queryParam("sort", "FRIEND_DESC")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void delete_correctId_success() throws Exception {
        // given
        doNothing().when(friendApplication).delete(anyLong(), anyLong());

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/friends/1")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isNoContent());
    }

}
