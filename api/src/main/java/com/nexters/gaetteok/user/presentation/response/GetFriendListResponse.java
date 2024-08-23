package com.nexters.gaetteok.user.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexters.gaetteok.domain.FriendWalkStatus;
import com.nexters.gaetteok.user.constant.SortCondition;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;

@Getter
public class GetFriendListResponse {

    @JsonProperty("items")
    @Schema(description = "친구 목록")
    private List<GetFriendResponse> friendList;

    public GetFriendListResponse(List<GetFriendResponse> friendList) {
        this.friendList = friendList;
    }

    public static GetFriendListResponse of(List<FriendWalkStatus> myFriendList,
        SortCondition sortCondition) {
        return new GetFriendListResponse(myFriendList.stream()
            .sorted(Comparator.comparing(sortCondition.getComparator()))
            .map(GetFriendResponse::of)
            .toList());
    }
}
