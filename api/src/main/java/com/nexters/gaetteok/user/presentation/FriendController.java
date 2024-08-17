package com.nexters.gaetteok.user.presentation;

import com.nexters.gaetteok.common.auth.UserInfo;
import com.nexters.gaetteok.domain.Friend;
import com.nexters.gaetteok.domain.FriendWalkStatus;
import com.nexters.gaetteok.user.FriendSpecification;
import com.nexters.gaetteok.user.application.FriendApplication;
import com.nexters.gaetteok.user.constant.SortCondition;
import com.nexters.gaetteok.user.presentation.request.CreateFriendRequest;
import com.nexters.gaetteok.user.presentation.response.CreateFriendResponse;
import com.nexters.gaetteok.user.presentation.response.GetFriendListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController implements FriendSpecification {

    private final FriendApplication friendApplication;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateFriendResponse> create(@RequestBody CreateFriendRequest request,
                                                       UserInfo userInfo) {
        long userId = userInfo.getUserId();
        log.info("[친구 관계 생성] userInfo={}, request={}", userInfo, request);
        Friend friend = friendApplication.create(userId, request.getCode());
        log.info("[친구 관계 생성 완료] friend={}", friend);
        return ResponseEntity.ok(CreateFriendResponse.of(friend));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetFriendListResponse> getList(@RequestParam(name = "sort") SortCondition sortCondition,
                                                         UserInfo userInfo) {
        List<FriendWalkStatus> friendList = friendApplication.getWalkStatusList(userInfo.getUserId());
        return ResponseEntity.ok(GetFriendListResponse.of(friendList, sortCondition));
    }

}
