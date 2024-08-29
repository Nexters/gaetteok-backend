package com.nexters.gaetteok.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "comment", indexes = {
    @Index(name = "idx_comment_walk_log_id", columnList = "walk_log_id")
}
)
@Getter
@EntityListeners(AuditingEntityListener.class)
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

    private boolean deleted;

    @Builder
    public CommentEntity(long id, String content, long userId, long walkLogId,
                         LocalDateTime createdAt, boolean deleted) {
        this.id = id;
        this.walkLogId = walkLogId;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.deleted = deleted;
    }
}
