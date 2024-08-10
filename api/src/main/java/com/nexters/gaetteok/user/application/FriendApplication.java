package com.nexters.gaetteok.user.application;

import com.nexters.gaetteok.domain.Friend;
import com.nexters.gaetteok.persistence.entity.FriendEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.repository.FriendRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.user.mapper.FriendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendApplication {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    public Friend create(long userId, String code) {
        UserEntity me = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. userId: " + userId));
        UserEntity friendUser = userRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("해당 친구 코드를 가진 사용자가 존재하지 않습니다. code: " + code));
        FriendEntity friend = friendRepository.save(FriendEntity.builder()
                .myUserId(me.getId())
                .friendUserId(friendUser.getId())
                .build());
        return FriendMapper.toDomain(friend, me, friendUser);
    }

    public List<Friend> getMyFriendList(long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. userId: " + userId));
        List<FriendEntity> friendList = friendRepository.findByMyUserId(user.getId());
        List<Long> friendIdList = friendList.stream().map(FriendEntity::getFriendUserId).toList();
        Map<Long, UserEntity> friendUserMap = userRepository.findAllById(friendIdList).stream().collect(Collectors.toMap(UserEntity::getId, e -> e));
        return friendList.stream().map(friend -> FriendMapper.toDomain(friend, user, friendUserMap.get(friend.getId()))).toList();
    }

}
