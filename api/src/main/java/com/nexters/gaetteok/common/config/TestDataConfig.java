package com.nexters.gaetteok.common.config;

import com.nexters.gaetteok.common.service.TestDataInjector;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class TestDataConfig {

    private final TestDataInjector testDataInjector;

    @PostConstruct
    public void init() {
        testDataInjector.initTestData();
    }

    @Scheduled(cron = "0 0 6 * * ?")
    public void poppyWalk() {
        log.info("오전 6시 뽀삐의 가짜 산책");
        testDataInjector.fakeWalk(1L);
    }

    @Scheduled(cron = "0 0 14 * * ?")
    public void chocoWalk() {
        log.info("오후 2시 초코의 가짜 산책");
        testDataInjector.fakeWalk(2L);
    }

}
