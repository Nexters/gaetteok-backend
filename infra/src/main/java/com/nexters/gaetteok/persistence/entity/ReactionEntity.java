package com.nexters.gaetteok.persistence.entity;

import com.nexters.gaetteok.domain.ReactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "reaction", indexes = {
    @Index(name = "idx_walk_log_id", columnList = "walk_log_id")
})
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "walk_log_id")
    private long walkLogId;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction_type")
    private ReactionType reactionType;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public ReactionEntity(long id, long userId, long walkLogId, ReactionType reactionType, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.walkLogId = walkLogId;
        this.reactionType = reactionType;
        this.createdAt = createdAt;
    }

}
