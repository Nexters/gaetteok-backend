package com.nexters.gaetteok.common.service;

import com.nexters.gaetteok.domain.WalkLog;
import com.nexters.gaetteok.domain.WalkTime;
import com.nexters.gaetteok.persistence.entity.FriendEntity;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.WalkLogEntity;
import com.nexters.gaetteok.user.mapper.FriendMapper;
import com.nexters.gaetteok.user.mapper.UserMapper;
import com.nexters.gaetteok.walklog.mapper.WalkLogMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestDataInjector {

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public void initTestData() {
        log.info("테스트 데이터 주입 - 실제 운영 시점에는 해당 코드는 제거되어야 함");

        UserEntity poppy = entityManager.merge(UserEntity.builder()
                .nickname("뽀삐")
                .profileUrl("https://placehold.co/400.png")
                .code("1a2b3c")
                .build());
        UserEntity choco = entityManager.merge(UserEntity.builder()
                .nickname("초코")
                .profileUrl("https://placehold.co/400.png")
                .code("4d5e6f")
                .build());
        UserEntity happy = entityManager.merge(UserEntity.builder()
                .nickname("해피")
                .profileUrl("https://placehold.co/400.png")
                .code("7g8h9i")
                .build());
        log.info("사용자 데이터 주입 뽀삐={} 초코={} 해피={}", UserMapper.toDomain(poppy), UserMapper.toDomain(choco), UserMapper.toDomain(happy));

        FriendEntity poppyToChoco = entityManager.merge(FriendEntity.builder()
                .myUserId(poppy.getId())
                .friendUserId(choco.getId())
                .build());
        FriendEntity chocoToPoppy = entityManager.merge(FriendEntity.builder()
                .myUserId(choco.getId())
                .friendUserId(poppy.getId())
                .build());
        FriendEntity poppyToHappy = entityManager.merge(FriendEntity.builder()
                .myUserId(poppy.getId())
                .friendUserId(happy.getId())
                .build());
        FriendEntity happyToPoppy = entityManager.merge(FriendEntity.builder()
                .myUserId(happy.getId())
                .friendUserId(poppy.getId())
                .build());
        log.info("친구 관계 생성 poppy-choco={} poppy-happy={}", FriendMapper.toDomain(poppyToChoco, poppy, choco), FriendMapper.toDomain(poppyToChoco, poppy, happy));

        List<WalkLog> walkLogList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            WalkLogEntity walkLogEntity = entityManager.merge(WalkLogEntity.builder()
                    .userId(poppy.getId())
                    .photoUrl("https://placehold.co/400.png")
                    .title("산책 끝~")
                    .content("오늘 산책 짱 재밌었당")
                    .walkTime(WalkTime.BETWEEN_20_AND_40_MINUTES)
                    .createdAt(LocalDateTime.now().minusDays(20-i))
                    .build());
            walkLogList.add(WalkLogMapper.toDomain(walkLogEntity, poppy));
        }
        log.info("뽀삐 산책 기록 저장: {}", walkLogList);

        WalkLogEntity chocoWalkLog = entityManager.merge(WalkLogEntity.builder()
                .userId(choco.getId())
                .photoUrl("https://placehold.co/400.png")
                .title("나두 산책했더")
                .content("날씨 개좋아")
                .walkTime(WalkTime.BETWEEN_20_AND_40_MINUTES)
                .createdAt(LocalDateTime.now().minusDays(1))
                .build());
        log.info("초코 산책 기록 저장: {}", chocoWalkLog);

        log.info("테스트 데이터 주입 완료");
    }

    /**
     * 가짜 산책
     */
    @Transactional
    public void fakeWalk(long userId) {
        entityManager.merge(WalkLogEntity.builder()
                .userId(userId)
                .photoUrl("https://placehold.co/400.png")
                .title("가짜 산책")
                .content("오늘의 가짜 산책 인증~ Good")
                .walkTime(WalkTime.WITHIN_20_MINUTES)
                .createdAt(LocalDateTime.now())
                .build());
    }

}
