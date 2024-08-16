package com.nexters.gaetteok.walklog.presentation;

import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import com.nexters.gaetteok.walklog.presentation.request.CreateWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.request.PatchWalkLogRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WalkLogControllerTests extends AbstractControllerTests {

    @Test
    void create_correctData_success() throws Exception {
        // given
        WalkLog walkLog = WalkLog.builder()
            .id(1L)
            .title("산책굳")
            .content("오늘 산책 짱 좋았당")
            .photoUrl("https://photo-url/walklog/20240810.jpg")
            .walkTime(WalkTime.BETWEEN_20_AND_40_MINUTES)
            .writerNickname("뽀삐")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        CreateWalkLogRequest request = new CreateWalkLogRequest(
            "산책굳", "오늘 산책 짱 좋았당", WalkTime.BETWEEN_20_AND_40_MINUTES);
        given(walkLogApplication.create(anyLong(), any(), any())).willReturn(walkLog);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.multipart("/api/walk-logs")
                .file(new MockMultipartFile("request", "", "application/json",
                    objectMapper.writeValueAsBytes(request)
                ))
                .file(new MockMultipartFile("photo", "photo".getBytes()))
                .contentType(MediaType.MULTIPART_FORM_DATA));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getCalendar_correctId_success() throws Exception {
        // given
        WalkLog walkLog = WalkLog.builder()
            .id(2L)
            .title("산책굳")
            .content("오늘 산책 짱 좋았당")
            .photoUrl("https://photo-url/walklog/20240810.jpg")
            .walkTime(WalkTime.BETWEEN_20_AND_40_MINUTES)
            .writerNickname("초코")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        WalkLog walkLog2 = WalkLog.builder()
            .id(2L)
            .title("산책 또 굳")
            .content("오늘 산책도 짱 좋았당")
            .photoUrl("https://photo-url/walklog/20240810.jpg")
            .walkTime(WalkTime.WITHIN_20_MINUTES)
            .writerNickname("초코")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .createdAt(LocalDateTime.now())
            .build();

        Map<String, WalkLog> walkLogs = new LinkedHashMap<>();
        walkLogs.put("2024-08-13", walkLog);
        walkLogs.put("2024-08-14", walkLog2);

        given(walkLogApplication.getCalendar(anyLong(), anyInt(), anyInt())).willReturn(walkLogs);

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/walk-logs/calendar")
                .param("userId", "2")
                .param("year", "2024")
                .param("month", "8")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getList_correctId_success() throws Exception {
        // given
        Comment comment = Comment.builder()
            .id(1L)
            .walkLogId(2L)
            .writerNickname("초코")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .content("댓글댓글댓글")
            .createdAt(LocalDateTime.now())
            .build();

        WalkLog walkLog = WalkLog.builder()
            .id(2L)
            .title("산책굳")
            .content("오늘 산책 짱 좋았당")
            .photoUrl("https://photo-url/walklog/20240810.jpg")
            .walkTime(WalkTime.BETWEEN_20_AND_40_MINUTES)
            .writerNickname("초코")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        WalkLog walkLog2 = WalkLog.builder()
            .id(3L)
            .title("산책 또 굳")
            .content("오늘 산책도 짱 좋았당")
            .photoUrl("https://photo-url/walklog/20240810.jpg")
            .walkTime(WalkTime.WITHIN_20_MINUTES)
            .writerNickname("초코")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .createdAt(LocalDateTime.now())
            .build();

        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(comment);
        walkLog.setComments(comments);
        walkLog2.setComments(comments);
        given(walkLogApplication.getList(anyLong(), anyLong(), anyInt())).willReturn(
            List.of(walkLog, walkLog2));

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/walk-logs")
                .param("userId", "2")
                .param("cursorId", "15")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getMyList_correctId_success() throws Exception {
        // given
        Comment comment = Comment.builder()
            .id(1L)
            .walkLogId(2L)
            .writerNickname("초코")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .content("댓글댓글댓글")
            .createdAt(LocalDateTime.now())
            .build();
        WalkLog walkLog = WalkLog.builder()
            .id(1L)
            .title("산책굳")
            .content("오늘 산책 짱 좋았당")
            .photoUrl("https://photo-url/walklog/20240810.jpg")
            .walkTime(WalkTime.BETWEEN_20_AND_40_MINUTES)
            .writerNickname("뽀삐")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        WalkLog walkLog2 = WalkLog.builder()
            .id(2L)
            .title("산책 또 굳")
            .content("오늘 산책도 짱 좋았당")
            .photoUrl("https://photo-url/walklog/20240810.jpg")
            .walkTime(WalkTime.WITHIN_20_MINUTES)
            .writerNickname("뽀삐")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(comment);
        walkLog.setComments(comments);
        walkLog2.setComments(comments);
        given(walkLogApplication.getListById(anyLong(), anyInt(), anyInt())).willReturn(
            List.of(walkLog, walkLog2));

        // when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/walk-logs/me")
                .param("year", String.valueOf(LocalDate.now().getYear()))
                .param("month", String.valueOf(LocalDate.now().getMonthValue()))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void update_correctData_success() throws Exception {
        // given
        WalkLog walkLog = WalkLog.builder()
            .id(1L)
            .title("산책굳")
            .content("오늘 산책 짱 좋았당")
            .photoUrl("https://photo-url/walklog/20240810.jpg")
            .walkTime(WalkTime.BETWEEN_20_AND_40_MINUTES)
            .writerNickname("뽀삐")
            .writerProfileImageUrl("https://photo-url/walklog/20240810.jpg")
            .createdAt(LocalDateTime.now())
            .build();
        PatchWalkLogRequest request = new PatchWalkLogRequest(
            "산책굳", "오늘 산책 짱 좋았당", WalkTime.BETWEEN_20_AND_40_MINUTES);
        given(walkLogApplication.update(eq(1L), anyLong(), any(), any())).willReturn(walkLog);

        long id = 1L;
        // when
        MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart(
            "/api/walk-logs/{id}", id);

        builder.with(request1 -> {
            request1.setMethod(HttpMethod.PATCH.name());
            return request1;
        });

        ResultActions resultActions = mockMvc.perform(
            builder
                .file(new MockMultipartFile("request", "", "application/json",
                    objectMapper.writeValueAsBytes(request)
                ))
                .file(new MockMultipartFile("photo", "photo".getBytes()))
                .contentType(MediaType.MULTIPART_FORM_DATA));

        // then
        resultActions.andExpect(status().isOk());
    }

}
