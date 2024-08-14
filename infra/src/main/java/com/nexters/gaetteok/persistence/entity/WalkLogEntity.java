package com.nexters.gaetteok.persistence.entity;

import com.nexters.gaetteok.domain.WalkTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "walk_log", indexes = {
        @Index(name = "idx_walk_log_user_id", columnList = "user_id")
})
@Getter
// FIXME : 테스트 데이터 주입 때문에 임시로 생성일자 직접 주입 방식으로 돌림 (테스트 데이터 주입 코드 제거할 때 주석 해제)
//@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String photoUrl;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "walk_time")
    private WalkTime walkTime;

    @Column(name = "user_id")
    private long userId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public WalkLogEntity(long id, String photoUrl, String title, String content, WalkTime walkTime, long userId, LocalDateTime createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.userId = userId;
        this.createdAt = createdAt;
    }

}
