package com.nexters.gaetteok.firebase.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushNotificationService {

    private final UserRepository userRepository;

    public void sendPickNotification(long userId) throws BadRequestException {
        UserEntity userEntity = userRepository.getById(userId);
        if (userEntity.getDeviceToken() == null) {
            throw new BadRequestException("device token should not be null!!");
        }

        String title = "\uD83D\uDC48" + " " + userEntity.getNickname() + " " + "얼른 산책할까요?";
        String body = "오늘 산책하지 않았나요? 친구가 찔렀어요!";

        sendMessage(title, body, userEntity.getDeviceToken());
    }

    public void sendEncouragementNotification(long userId) throws BadRequestException {
        UserEntity userEntity = userRepository.getById(userId);
        if (userEntity.getDeviceToken() == null) {
            throw new BadRequestException("device token should not be null!!");
        }

        String title = "\uD83D\uDC36 산책갈 시간이예요!";
        String body = userEntity.getNickname() + "와 산책나갈 준비 해볼까요?";

        sendMessage(title, body, userEntity.getDeviceToken());
    }

    private static void sendMessage(String title, String body, String token) {
        try {
            FirebaseMessaging.getInstance().send(Message.builder()
                .setNotification(Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build())
                .setToken(token)  // 대상 디바이스의 등록 토큰
                .build());
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }

}
