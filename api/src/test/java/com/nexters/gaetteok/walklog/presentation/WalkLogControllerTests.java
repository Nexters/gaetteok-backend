package com.nexters.gaetteok.walklog.presentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.nexters.gaetteok.common.presentation.AbstractControllerTests;
import com.nexters.gaetteok.domain.Comment;
import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import com.nexters.gaetteok.walklog.presentation.request.CreateWalkLogRequest;
import com.nexters.gaetteok.walklog.presentation.request.PatchWalkLogRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

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
            RestDocumentationRequestBuilders.multipart("/api/walk-logs")
                .file(new MockMultipartFile("request", "", "application/json",
                    objectMapper.writeValueAsBytes(request)
                ))
                .file(new MockMultipartFile("photo", "photo".getBytes()))
                .contentType(MediaType.MULTIPART_FORM_DATA));

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "산책 기록 등록",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestParts(
                    partWithName("request").description("요청 데이터"),
                    partWithName("photo").description("산책 기록 사진")
                ),
                requestPartFields(
                    "request",
                    fieldWithPath("title").description("산책 기록 제목"),
                    fieldWithPath("content").description("산책 기록 내용"),
                    fieldWithPath("walkTime").description("산책한 시간")
                ),
                responseFields(
                    fieldWithPath("id").description("산책 기록 ID"),
                    fieldWithPath("photoUrl").description("산책 기록 사진 URL"),
                    fieldWithPath("title").description("산책 기록 제목"),
                    fieldWithPath("content").description("산책 기록 내용"),
                    fieldWithPath("walkTime").description("산책한 시간"),
                    fieldWithPath("writerNickname").description("작성자 닉네임"),
                    fieldWithPath("writerProfileImageUrl").description("작성자 프로필 URL"),
                    fieldWithPath("createdAt").description("등록 시간")
                ),
                resource(ResourceSnippetParameters.builder()
                    .tag("WalkLog")
                    .summary("산책 기록을 등록하는 API")
                    .build())
            ));

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
            RestDocumentationRequestBuilders.get("/api/walk-logs/calendar")
                .param("userId", "2")
                .param("year", "2024")
                .param("month", "8")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "산책 캘린더 조회",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("WalkLog")
                    .summary("산책 기록 캘린더를 조회하는 API")
                    .queryParameters(
                        parameterWithName("userId").description("사용자 아이디.").optional(),
                        parameterWithName("year").description("년도").optional(),
                        parameterWithName("month").description("월").optional()
                    )
                    .build())
            ));
    }

    @Test
    void getListById_correctId_success() throws Exception {
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
        given(walkLogApplication.getListById(anyLong(), anyLong(), anyInt())).willReturn(
            List.of(walkLog, walkLog2));

        // when
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/walk-logs")
                .param("userId", "2")
                .param("cursorId", "15")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "산책 기록 목록 조회",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("WalkLog")
                    .summary("산책 기록 목록을 조회하는 API")
                    .queryParameters(
                        parameterWithName("userId").description(
                            "사용자 아이디. 생략하면 나 포함 모든 친구의 산책 기록 조회").optional(),
                        parameterWithName("cursorId").description(
                            "커서. 해당 아이디값보다 아이디가 작은 산책 기록을 조회. 없으면 가장 최근 것부터 조회").optional(),
                        parameterWithName("pageSize").description("한 번에 조회할 산책 기록 수. 기본값은 10개")
                            .optional()
                    )
                    .responseFields(
                        fieldWithPath("items[0].id").description("산책 기록 ID"),
                        fieldWithPath("items[0].photoUrl").description("산책 기록 사진 URL"),
                        fieldWithPath("items[0].title").description("산책 기록 제목"),
                        fieldWithPath("items[0].content").description("산책 기록 내용"),
                        fieldWithPath("items[0].walkTime").description("산책한 시간"),
                        fieldWithPath("items[0].writerNickname").description("작성자 닉네임"),
                        fieldWithPath("items[0].writerProfileImageUrl").description("작성자 프로필 URL"),
                        fieldWithPath("items[0].createdAt").description("등록 시간"),
                        fieldWithPath("items[0].comments[0].id").description("댓글 고유 id"),
                        fieldWithPath("items[0].comments[0].content").description("내용"),
                        fieldWithPath("items[0].comments[0].writerNickname").description("작성자 닉네임"),
                        fieldWithPath("items[0].comments[0].writerProfileImageUrl").description(
                            "작성자 프로필 이미지"),
                        fieldWithPath("items[0].comments[0].walkLogId").description(
                            "게시글 ID"),
                        fieldWithPath("items[0].comments[0].createdAt").description(
                            "댓글 등록 시간")
                    )
                    .build())
            ));
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
        given(walkLogApplication.getListById(anyLong(), anyLong(), anyInt())).willReturn(
            List.of(walkLog, walkLog2));

        // when
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/walk-logs/me")
                .param("cursorId", "15")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "내 산책 기록 목록 조회",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("WalkLog")
                    .summary("내 산책 기록 목록을 조회하는 API")
                    .queryParameters(
                        parameterWithName("cursorId").description(
                            "커서. 해당 아이디값보다 아이디가 작은 산책 기록을 조회. 없으면 가장 최근 것부터 조회").optional(),
                        parameterWithName("pageSize").description("한 번에 조회할 산책 기록 수. 기본값은 10개")
                            .optional()
                    )
                    .responseFields(
                        fieldWithPath("items[0].id").description("산책 기록 ID"),
                        fieldWithPath("items[0].photoUrl").description("산책 기록 사진 URL"),
                        fieldWithPath("items[0].title").description("산책 기록 제목"),
                        fieldWithPath("items[0].content").description("산책 기록 내용"),
                        fieldWithPath("items[0].walkTime").description("산책한 시간"),
                        fieldWithPath("items[0].writerNickname").description("작성자 닉네임"),
                        fieldWithPath("items[0].writerProfileImageUrl").description("작성자 프로필 URL"),
                        fieldWithPath("items[0].createdAt").description("등록 시간"),
                        fieldWithPath("items[0].comments[0].id").description("댓글 고유 id"),
                        fieldWithPath("items[0].comments[0].content").description("내용"),
                        fieldWithPath("items[0].comments[0].writerNickname").description("작성자 닉네임"),
                        fieldWithPath("items[0].comments[0].writerProfileImageUrl").description(
                            "작성자 프로필 이미지"),
                        fieldWithPath("items[0].comments[0].walkLogId").description(
                            "게시글 ID"),
                        fieldWithPath("items[0].comments[0].createdAt").description(
                            "댓글 등록 시간")
                    )
                    .build())
            ));
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
        MockMultipartHttpServletRequestBuilder builder = RestDocumentationRequestBuilders.multipart(
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
        resultActions
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document(
                "산책 기록 변경",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestParts(
                    partWithName("request").description("요청 데이터"),
                    partWithName("photo").description("산책 기록 사진")
                ),
                requestPartFields(
                    "request",
                    fieldWithPath("title").description("산책 기록 제목"),
                    fieldWithPath("content").description("산책 기록 내용")
                ),
                responseFields(
                    fieldWithPath("id").description("산책 기록 ID"),
                    fieldWithPath("photoUrl").description("산책 기록 사진 URL"),
                    fieldWithPath("title").description("산책 기록 제목"),
                    fieldWithPath("content").description("산책 기록 내용"),
                    fieldWithPath("walkTime").description("산책한 시간"),
                    fieldWithPath("writerNickname").description("작성자 닉네임"),
                    fieldWithPath("writerProfileImageUrl").description("작성자 프로필 URL"),
                    fieldWithPath("createdAt").description("등록 시간")
                ),
                resource(ResourceSnippetParameters.builder()
                    .tag("WalkLog")
                    .summary("산책 기록을 변경하는 API")
                    .build())
            ));

    }

}
