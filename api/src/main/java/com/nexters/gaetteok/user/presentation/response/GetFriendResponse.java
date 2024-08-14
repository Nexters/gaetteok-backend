package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.Friend;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetFriendResponse {

    private long id;

    private String nickname;

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
