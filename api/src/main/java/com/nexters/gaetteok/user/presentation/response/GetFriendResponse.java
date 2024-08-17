package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.FriendWalkStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetFriendResponse {

    @Schema(description = "친구의 사용자 아이디", example = "2")
    private long id;

    @Schema(description = "친구의 닉네임", example = "초코")
    private String nickname;

    @Schema(description = "친구의 프로필 이미지 URL", example = "https://profile-image.jpg")
    private String profileImageUrl;

    @Schema(description = "산책 완료 여부", example = "true")
    private boolean walkDone;

    @Builder
    public GetFriendResponse(long id, String nickname, String profileImageUrl, boolean walkDone) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.walkDone = walkDone;
    }

    public static GetFriendResponse of(FriendWalkStatus friendWalkStatus) {
        return GetFriendResponse.builder()
            .id(friendWalkStatus.getId())
            .nickname(friendWalkStatus.getNickname())
            .profileImageUrl(friendWalkStatus.getProfileImageUrl())
            .walkDone(friendWalkStatus.isDone())
            .build();
    }

}
