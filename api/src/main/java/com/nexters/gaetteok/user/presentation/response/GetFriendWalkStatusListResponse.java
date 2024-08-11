package com.nexters.gaetteok.user.presentation.response;

import com.nexters.gaetteok.domain.FriendWalkStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFriendWalkStatusListResponse {

    List<FriendWalkStatus> friendWalkStatusList;

    public GetFriendWalkStatusListResponse(List<FriendWalkStatus> friendWalkStatusList) {
        this.friendWalkStatusList = friendWalkStatusList;
    }

}
