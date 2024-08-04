package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.Friend;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFriendListResponse {

    private List<GetFriendResponse> friendList;

    public GetFriendListResponse(List<GetFriendResponse> friendList) {
        this.friendList = friendList;
    }

    public static GetFriendListResponse of(List<Friend> myFriendList) {
        return new GetFriendListResponse(myFriendList.stream()
                .map(GetFriendResponse::of)
                .toList());
    }
}
