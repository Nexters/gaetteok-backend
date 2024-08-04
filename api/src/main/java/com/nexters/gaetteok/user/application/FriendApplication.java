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

@Service
@RequiredArgsConstructor
public class FriendApplication {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    public Friend create(long userId, String code) {
        UserEntity me = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. userId: " + userId));
        UserEntity friend = userRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("해당 친구 코드를 가진 사용자가 존재하지 않습니다. code: " + code));
        return FriendMapper.toDomain(friendRepository.save(FriendEntity.builder()
                .me(me)
                .friend(friend)
                .build()));
    }

    public List<Friend> getMyFriendList(long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. userId: " + userId));
        List<FriendEntity> friendList = friendRepository.findByMe(user);
        return friendList.stream().map(FriendMapper::toDomain).toList();
    }

}
