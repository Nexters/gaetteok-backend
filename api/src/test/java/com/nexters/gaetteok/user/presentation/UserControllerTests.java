package com.nexters.gaetteok.user.presentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


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
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/users", id)
                .contentType("application/json"));

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "사용자 정보 조회",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("User")
                    .summary("사용자 정보 조회 API")
                    .responseFields(
                        fieldWithPath("id").description("사용자 ID"),
                        fieldWithPath("nickname").description("사용자 닉네임"),
                        fieldWithPath("profileImageUrl").description("사용자 프로필 사진 경로"),
                        fieldWithPath("code").description("사용자 고유 친구 추천 코드"),
                        fieldWithPath("createdAt").description("계정 생성 일자")
                    )
                    .build())
            ));
    }

    @Test
    void patchUser_location_success() throws Exception {
        // given
        long id = 1L;
        User user = User.builder()
            .id(id)
            .nickname("test")
            .code("123456")
            .location("daegu")
            .profileUrl("https://profile-image.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        given(userApplication.updateUser(anyLong(), anyString())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.patch("/api/users/location")
                .param("location", "seoul")
                .contentType("application/json"));

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "사용자 위치 정보 변경",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("User")
                    .summary("사용자 위치 정보 변경 API")
                    .responseFields(
                        fieldWithPath("location").description("변경된 location 정보")
                    )
                    .build())
            ));
    }

}
