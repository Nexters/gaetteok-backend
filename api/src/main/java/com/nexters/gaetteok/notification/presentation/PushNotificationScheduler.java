package com.nexters.gaetteok.notification.presentation;

import com.nexters.gaetteok.firebase.service.PushNotificationService;
import com.nexters.gaetteok.user.application.UserApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class PushNotificationScheduler {

    private final PushNotificationService pushNotificationService;
    private final UserApplication userApplication;

    // 일단 당분간은 주석!
//    @Scheduled(fixedDelay = 60000)
//    public void run() {
//        log.info("스케쥴러 동작 시작");
//        LocalDateTime now = LocalDateTime.now();
//        int targetMinute = (now.getHour() * 60) + now.getMinute();
//        userApplication.getPushNotificationByMinute(targetMinute).forEach(
//            pushNotification -> {
//                log.info("{} 동작 시작", pushNotification.getUserId());
//                pushNotificationService.sendEncouragementNotification(pushNotification.getUserId());
//            }
//        );
//    }
}
