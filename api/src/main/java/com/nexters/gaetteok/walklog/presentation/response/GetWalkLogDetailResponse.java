package com.nexters.gaetteok.walklog.presentation.response;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import com.nexters.gaetteok.jwt.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
public class GetWalkLogDetailResponse {

    @Schema(description = "산책 기록 ID", example = "1")
    private long id;

    @Schema(description = "산책 기록 사진 URL", example = "https://gaetteok.com/1.jpg")
    private String photoUrl;

    @Schema(description = "산책 기록 제목", example = "산책 기록 제목")
    private String title;

    @Schema(description = "산책 기록 내용", example = "산책 기록 내용")
    private String content;

    @Schema(description = "산책 시간", examples = {"20분 내외", "20분~40분", "40분~1시간"})
    private WalkTime walkTime;

    @Schema(description = "작성자 ID", example = "1")
    private long writerId;

    @Schema(description = "작성자 닉네임", example = "뽀삐")
    private String writerNickname;

    @Schema(description = "작성자 프로필 이미지 URL", example = "https://gaetteok.com/profile.jpg")
    private String writerProfileImageUrl;

    @Schema(description = "작성자가 본인인지 여부", example = "true")
    private boolean isMe;

    @Schema(description = "댓글 리스트")
    private List<GetCommentResponse> comments;

    @Schema(description = "리액션 리스트")
    private List<GetReactionResponse> reactions;

    @Schema(description = "작성일", example = "2021-07-01T00:00:00")
    private LocalDateTime createdAt;

    @Builder
    public GetWalkLogDetailResponse(long id, String photoUrl, String title, String content,
                                    WalkTime walkTime, long writerId, String writerNickname, String writerProfileImageUrl,
                                    boolean isMe, List<GetCommentResponse> comments, List<GetReactionResponse> reactions,
                                    LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerProfileImageUrl = writerProfileImageUrl;
        this.isMe = isMe;
        this.comments = comments;
        this.reactions = reactions;
        this.createdAt = createdAt;
    }

    public static GetWalkLogDetailResponse of(WalkLog walkLog, UserInfo userInfo) {
        List<GetCommentResponse> commentResponseList = walkLog.getComments() != null
            ? walkLog.getComments().stream().map(comment -> GetCommentResponse.of(comment, userInfo)).toList()
            : Collections.emptyList();
        List<GetReactionResponse> reactionResponseList = walkLog.getReactions() != null
            ? walkLog.getReactions().stream().map(reaction -> GetReactionResponse.of(reaction, userInfo)).toList()
            : Collections.emptyList();

        return GetWalkLogDetailResponse.builder()
            .id(walkLog.getId())
            .photoUrl(walkLog.getPhotoUrl())
            .title(walkLog.getTitle())
            .content(walkLog.getContent())
            .walkTime(walkLog.getWalkTime())
            .writerId(walkLog.getWriterId())
            .writerNickname(walkLog.getWriterNickname())
            .writerProfileImageUrl(walkLog.getWriterProfileImageUrl())
            .isMe(walkLog.getWriterId() == userInfo.getUserId())
            .comments(commentResponseList)
            .reactions(reactionResponseList)
            .createdAt(walkLog.getCreatedAt())
            .build();
    }

}
