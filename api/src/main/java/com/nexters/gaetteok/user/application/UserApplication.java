package com.nexters.gaetteok.user.application;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.domain.UserPushNotification;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.UserPushNotificationEntity;
import com.nexters.gaetteok.persistence.repository.UserPushNotificationRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserApplication {

    private final UserRepository userRepository;
    private final UserPushNotificationRepository userPushNotificationRepository;

    private UserPushNotificationEntity toPushNotificationEntity(
        UserPushNotification userPushNotificationDomain) {
        return new UserPushNotificationEntity(
            userPushNotificationDomain.getId(),
            userPushNotificationDomain.getUserId(),
            userPushNotificationDomain.getPushNotificationTime()
        );
    }

    private UserPushNotification toUserPushNotificationDomain(
        UserPushNotificationEntity userPushNotificationEntity) {
        return new UserPushNotification(
            userPushNotificationEntity.getId(),
            userPushNotificationEntity.getUserId(),
            userPushNotificationEntity.getPushNotificationTime()
        );
    }

    public long getPushNotificationTime(long id) {
        UserPushNotificationEntity userPushNotification = userPushNotificationRepository.findByUserId(
            id);

        return userPushNotification.getPushNotificationTime();
    }

    public User getUser(long id) {
        UserEntity userEntity = userRepository.getById(id);
        return UserMapper.toDomain(userEntity);
    }

    public void updatePushNotificationTime(long id, long timeToBeUpdated) {
        UserPushNotificationEntity userPushNotification = userPushNotificationRepository.findByUserId(
            id);

        UserPushNotification userPushNotificationDomain = toUserPushNotificationDomain(
            userPushNotification);

        userPushNotificationDomain.setPushNotificationTime(timeToBeUpdated);

        UserPushNotificationEntity updatedEntity = toPushNotificationEntity(
            userPushNotificationDomain);

        userPushNotificationRepository.save(updatedEntity);
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
    public User updateLocation(long id, String city) {
        UserEntity userEntity = userRepository.getById(id);
        User user = UserMapper.toDomain(userEntity);
        user.updateLocation(city);
        userEntity = userRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(userEntity);
    }

}
