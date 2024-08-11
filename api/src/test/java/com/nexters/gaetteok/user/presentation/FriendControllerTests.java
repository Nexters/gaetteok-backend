package com.nexters.gaetteok.user.presentation;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.Friend;
import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.user.presentation.request.CreateFriendRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
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
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/api/friends")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentationWrapper.document(
                        "친구 추가",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Friend")
                                .summary("친구 코드로 친구를 추가하는 API")
                                .requestFields(
                                        fieldWithPath("code").description("친구 코드")
                                )
                                .responseFields(
                                        fieldWithPath("friendNickname").description("추가된 친구의 닉네임"),
                                        fieldWithPath("friendProfileUrl").description("추가된 친구의 프로필 이미지 URL")
                                )
                                .build())
                ));
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
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/api/friends")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentationWrapper.document(
                        "친구 목록 조회",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("Friend")
                                .summary("내 친구 목록을 조회하는 API")
                                .responseFields(
                                        fieldWithPath("friendList[0].nickname").description("친구의 닉네임"),
                                        fieldWithPath("friendList[0].profileUrl").description("친구의 프로필 이미지 URL")
                                )
                                .build())
                ));
    }

}
