package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.Reaction;
import com.nexters.gaetteok.domain.ReactionType;
import com.nexters.gaetteok.walklog.presentation.request.CreateReactionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReactionControllerTests extends AbstractControllerTests {

    @Test
    void create_correctData_success() throws Exception {
        // given
        Reaction reaction = Reaction.builder()
            .id(1L)
            .walkLogId(1L)
            .writerNickname("뽀삐")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .reactionType(ReactionType.LIKE)
            .createdAt(LocalDateTime.now())
            .build();
        CreateReactionRequest request = new CreateReactionRequest(ReactionType.LIKE);
        given(reactionApplication.create(anyLong(), anyLong(), any())).willReturn(reaction);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/walk-logs/1/reaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));


        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void delete_correctId_success() throws Exception {
        // given
        doNothing().when(reactionApplication).delete(anyLong(), anyLong());

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/walk-logs/1/reaction/1"));

        // then
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    void delete_notMyReaction_fail() throws Exception {
        // given
        doThrow(new IllegalArgumentException("자신이 작성한 리액션만 삭제할 수 있습니다.")).when(reactionApplication).delete(anyLong(), anyLong());

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/walk-logs/1/reaction/1"));

        // then
        resultActions.andExpect(status().isBadRequest());
    }

}
