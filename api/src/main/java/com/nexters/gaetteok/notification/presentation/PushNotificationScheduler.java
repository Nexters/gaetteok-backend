package com.nexters.gaetteok.notification.presentation;

import com.nexters.gaetteok.firebase.service.PushNotificationService;
import com.nexters.gaetteok.user.application.UserApplication;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class PushNotificationScheduler {

    private final PushNotificationService pushNotificationService;
    private final UserApplication userApplication;

    @Scheduled(fixedDelay = 60000)
    public void run() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        int targetMinute = (now.getHour() * 60) + now.getMinute();
        userApplication.getPushNotificationByMinute(targetMinute).forEach(
            pushNotification -> {
                try {
                    pushNotificationService.sendEncouragementNotification(
                        pushNotification.getUserId());
                } catch (BadRequestException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }
}
