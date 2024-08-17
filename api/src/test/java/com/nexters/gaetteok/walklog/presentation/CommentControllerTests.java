package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.walklog.presentation.request.CreateCommentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
            MockMvcRequestBuilders.post("/api/walk-logs/{id}/comments", comment.getWalkLogId())
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCommentRequest))
        );

        // then
        resultActions.andExpect(status().isOk());
    }

}
