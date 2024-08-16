package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.Friend;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateFriendResponse {

    private long friendId;

    private String friendNickname;

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
