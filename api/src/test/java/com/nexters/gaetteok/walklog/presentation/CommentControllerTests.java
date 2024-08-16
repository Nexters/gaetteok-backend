package com.nexters.gaetteok.walklog.presentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

public class CommentControllerTests extends AbstractControllerTests {

    @Test
    void create_correctData_success() throws Exception {
        // given
        Comment comment = Comment.builder()
            .id(1L)
            .walkLogId(2L)
            .writerNickname("초코")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .content("댓글댓글댓글")
            .createdAt(LocalDateTime.now())
            .build();

        given(commentApplication.create(anyLong(), anyLong(), any())).willReturn(comment);

        CreateCommentRequest createCommentRequest = new CreateCommentRequest("help");
        // when
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(
                    "/api/walk-logs/{id}/comments", comment.getWalkLogId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCommentRequest))
        );

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "댓글 생성",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("WalkLog")
                    .summary("댓글 생성 API")
                    .responseFields(
                        fieldWithPath("content").description("내용"),
                        fieldWithPath("writerNickname").description("작성자 닉네임"),
                        fieldWithPath("writerProfileImageUrl").description(
                            "작성자 프로필 이미지"),
                        fieldWithPath("walkLogId").description(
                            "게시글 ID"),
                        fieldWithPath("createdAt").description(
                            "댓글 등록 시간")
                    )
                    .build())
            ));
    }

}
