package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.Friend;
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

    @Builder
    public GetFriendResponse(long id, String nickname, String profileImageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public static GetFriendResponse of(Friend friend) {
        return GetFriendResponse.builder()
            .id(friend.getFriend().getId())
            .nickname(friend.getFriend().getNickname())
            .profileImageUrl(friend.getFriend().getProfileUrl())
            .build();
    }

}
