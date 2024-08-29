package com.nexters.gaetteok.user.application;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.domain.UserPushNotification;
import com.nexters.gaetteok.image.model.File;
import com.nexters.gaetteok.image.service.ImageUploader;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.UserPushNotificationEntity;
import com.nexters.gaetteok.persistence.repository.*;
import com.nexters.gaetteok.user.mapper.UserMapper;
import com.nexters.gaetteok.user.mapper.UserPushNotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApplication {

    private final ImageUploader imageUploader;

    private final UserRepository userRepository;
    private final UserPushNotificationRepository userPushNotificationRepository;

    private final FriendRepository friendRepository;
    private final WalkLogRepository walkLogRepository;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;

    @Transactional(readOnly = true)
    public User getUser(long id) {
        UserEntity userEntity = userRepository.getById(id);
        return UserMapper.toDomain(userEntity);
    }

    @Transactional
    public User updateNickname(long id, String nickname) {
        UserEntity userEntity = userRepository.getById(id);
        User user = UserMapper.toDomain(userEntity);
        user.updateNickname(nickname);
        userEntity = userRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(userEntity);
    }

    @Transactional
    public User updateProfile(long userId, MultipartFile profileImage) throws IOException {
        UserEntity userEntity = userRepository.getById(userId);
        User user = UserMapper.toDomain(userEntity);

        File newProfileImageFile = imageUploader.uploadFiles(List.of(profileImage), "profiles")
            .get(0);
        user.updateProfile(newProfileImageFile.getUploadFileUrl());
        userEntity = userRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(userEntity);
    }

    @Transactional
    public User updateLocation(long id, String city) {
        UserEntity userEntity = userRepository.getById(id);
        User user = UserMapper.toDomain(userEntity);
        user.updateLocation(city);
        userEntity = userRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(userEntity);
    }

    @Transactional(readOnly = true)
    public Integer getPushNotificationTime(long id) {
        UserPushNotificationEntity userPushNotification = userPushNotificationRepository.findByUserId(
            id);

        return userPushNotification.getPushNotificationTime();
    }

    @Transactional(readOnly = true)
    public List<UserPushNotification> getPushNotificationByMinute(long minute) {
        return userPushNotificationRepository.findByPushNotificationTime(
                minute)
            .stream()
            .map(
                userPushNotificationEntity -> UserPushNotificationMapper.toDomain(
                    userPushNotificationEntity)
            )
            .toList();
    }

    @Transactional
    public UserPushNotification updatePushNotificationTime(long id, Integer timeToBeUpdated) {
        UserPushNotificationEntity userPushNotificationEntity = userPushNotificationRepository.findByUserId(
            id);
        UserPushNotification userPushNotification = UserPushNotificationMapper.toDomain(
            userPushNotificationEntity);
        userPushNotification.setPushNotificationTime(timeToBeUpdated);

        userPushNotificationEntity = userPushNotificationRepository.save(
            UserPushNotificationMapper.toEntity(userPushNotification));
        return UserPushNotificationMapper.toDomain(userPushNotificationEntity);
    }

    @Transactional
    public void deleteUser(UserInfo userInfo) {
        long userId = userInfo.getUserId();
        UserEntity userEntity = userRepository.getById(userId);
        userEntity.delete();
        long deletedFriendCount = friendRepository.deleteByUserId(userId);
        long deletedWalkLogCount = walkLogRepository.deleteByUserId(userId);
        long deletedCommentCount = commentRepository.deleteByUserId(userId);
        long deletedReactionCount = reactionRepository.deleteByUserId(userId);
        log.info("사용자 {} 삭제 - 삭제된 친구 관계 수: {}, 산책 기록 수: {}, 댓글 수: {}, 리액션 수: {}",
            userInfo,
            deletedFriendCount,
            deletedWalkLogCount,
            deletedCommentCount,
            deletedReactionCount
        );
    }

    @Transactional
    public void restoreUser(long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        userEntity.restore();
        long restoreFriendCount = friendRepository.restoreByUserId(userId);
        long restoredWalkLogCount = walkLogRepository.restoreByUserId(userId);
        long restoredCommentCount = commentRepository.restoreByUserId(userId);
        long restoredReactionCount = reactionRepository.restoreByUserId(userId);
        log.info("사용자 {} 복구 - 복구된 친구 관계 수: {}, 산책 기록 수: {}, 댓글 수: {}, 리액션 수: {}",
            userId,
            restoreFriendCount,
            restoredWalkLogCount,
            restoredCommentCount,
            restoredReactionCount
        );
    }

}
