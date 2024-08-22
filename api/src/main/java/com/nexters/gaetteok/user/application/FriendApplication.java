package com.nexters.gaetteok.user.application;

import com.nexters.gaetteok.domain.Friend;
import com.nexters.gaetteok.domain.FriendWalkStatus;
import com.nexters.gaetteok.persistence.entity.FriendEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.repository.FriendRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.user.mapper.FriendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendApplication {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    @Transactional
    public Friend create(long userId, String code) {
        UserEntity me = userRepository.getById(userId);
        UserEntity friend = userRepository.findByCode(code)
            .orElseThrow(
                () -> new IllegalArgumentException("해당 친구 코드를 가진 사용자가 존재하지 않습니다. code: " + code));
        if (isAlreadyFriend(me.getId(), friend.getId())) {
            throw new IllegalArgumentException("이미 친구인 사용자의 코드입니다. 친구: " + friend.getNickname());
        }
        FriendEntity meToFriend = friendRepository.save(FriendEntity.builder()
            .myUserId(me.getId())
            .friendUserId(friend.getId())
            .build());
        FriendEntity friendToMe = friendRepository.save(FriendEntity.builder()
            .myUserId(friend.getId())
            .friendUserId(me.getId())
            .build());
        return FriendMapper.toDomain(meToFriend, me, friend);
    }

    @Transactional
    public void delete(long userId, long friendUserId) {
        FriendEntity meToFriend = friendRepository.getByMyUserIdAndFriendUserId(
            userId, friendUserId);
        FriendEntity friendToMe = friendRepository.getByMyUserIdAndFriendUserId(
            friendUserId, userId);
        friendRepository.delete(meToFriend);
        friendRepository.delete(friendToMe);
    }

    @Transactional(readOnly = true)
    public List<Friend> getMyFriendList(long userId) {
        UserEntity user = userRepository.getById(userId);
        List<FriendEntity> friendList = friendRepository.findByMyUserId(user.getId());
        List<Long> friendIdList = friendList.stream().map(FriendEntity::getFriendUserId).toList();
        Map<Long, UserEntity> friendUserMap = userRepository.findAllById(friendIdList).stream()
            .collect(Collectors.toMap(UserEntity::getId, e -> e));
        return friendList.stream().map(friend -> FriendMapper.toDomain(friend, user,
            friendUserMap.get(friend.getFriendUserId())
        )).toList();
    }

    @Transactional(readOnly = true)
    public List<FriendWalkStatus> getWalkStatusList(long userId) {
        return friendRepository.getFriendWalkStatus(userId, LocalDate.now());
    }

    private boolean isAlreadyFriend(long myUserId, long friendUserId) {
        return friendRepository.findByMyUserIdAndFriendUserId(myUserId, friendUserId).isPresent();
    }

}
