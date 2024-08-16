package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.Friend;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateFriendResponse {

    @Schema(description = "친구 아이디", example = "2")
    private long friendId;

    @Schema(description = "친구 닉네임", example = "초코")
    private String friendNickname;

    @Schema(description = "친구 프로필 이미지 URL", example = "https://profile-image.jpg")
    private String friendProfileImageUrl;

    @Builder
    public CreateFriendResponse(long friendId, String friendNickname,
        String friendProfileImageUrl) {
        this.friendId = friendId;
        this.friendNickname = friendNickname;
        this.friendProfileImageUrl = friendProfileImageUrl;
    }

    public static CreateFriendResponse of(Friend friend) {
        return CreateFriendResponse.builder()
            .friendId(friend.getFriend().getId())
            .friendNickname(friend.getFriend().getNickname())
            .friendProfileImageUrl(friend.getFriend().getProfileUrl())
            .build();
    }

}
