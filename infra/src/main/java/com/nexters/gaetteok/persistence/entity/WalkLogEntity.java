package com.nexters.gaetteok.persistence.entity;

import com.nexters.gaetteok.domain.WalkTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "walk_log", indexes = {
        @Index(name = "idx_walk_log_user_id", columnList = "user_id")
})
@Getter
@EntityListeners(AuditingEntityListener.class)
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

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public WalkLogEntity(long id, String photoUrl, String title, String content, WalkTime walkTime, long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.content = content;
        this.walkTime = walkTime;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
