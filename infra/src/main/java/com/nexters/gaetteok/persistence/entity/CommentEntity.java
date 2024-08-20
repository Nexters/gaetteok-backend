package com.nexters.gaetteok.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "comment", indexes = {
    @Index(name = "idx_comment_walk_log_id", columnList = "walk_log_id")
}
)
@Getter
// FIXME : 테스트 데이터 주입 때문에 임시로 생성일자 직접 주입 방식으로 돌림 (테스트 데이터 주입 코드 제거할 때 주석 해제)
//@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "walk_log_id")
    private long walkLogId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "user_id")
    private long userId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public CommentEntity(long id, String content, long userId, long walkLogId,
                         LocalDateTime createdAt) {
        this.id = id;
        this.walkLogId = walkLogId;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
