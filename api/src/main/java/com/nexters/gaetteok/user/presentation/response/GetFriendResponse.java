package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.Friend;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetFriendResponse {

    private String nickname;

    private String profileUrl;

    @Builder
    public GetFriendResponse(String nickname, String profileUrl) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

    public static GetFriendResponse of(Friend friend) {
        return GetFriendResponse.builder()
                .nickname(friend.getFriend().getNickname())
                .profileUrl(friend.getFriend().getProfileUrl())
                .build();
    }

}
