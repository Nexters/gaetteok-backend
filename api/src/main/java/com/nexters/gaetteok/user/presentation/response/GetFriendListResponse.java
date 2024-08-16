package com.nexters.gaetteok.user.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexters.gaetteok.domain.Friend;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFriendListResponse {

    @JsonProperty("items")
    @Schema(description = "친구 목록")
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
