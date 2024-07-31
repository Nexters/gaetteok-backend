package com.nexters.gaetteok.user.presentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.user.application.UserApplication;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
@ExtendWith(RestDocumentationExtension.class)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserApplication userApplication;

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
            RestDocumentationRequestBuilders.get("/api/users/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)));

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "사용자 정보 조회",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("User")
                    .summary("사용자 정보 조회 API")
                    .pathParameters(
                        parameterWithName("id").description("사용자 ID")
                    )
                    .responseFields(
                        fieldWithPath("id").description("사용자 ID"),
                        fieldWithPath("nickname").description("사용자 닉네임"),
                        fieldWithPath("profileUrl").description("사용자 프로필 사진 경로"),
                        fieldWithPath("code").description("사용자 고유 친구 추천 코드"),
                        fieldWithPath("createdAt").description("계정 생성 일자")
                    )
                    .build())
            ));
    }

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .build();
    }

}
