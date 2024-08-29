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
    name = "friend",
    indexes = {
        @Index(name = "idx_friend_my_user_id", columnList = "my_user_id")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "unique_friend_my_user_id_friend_user_id", columnNames = {"my_user_id", "friend_user_id"})
    }
)
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "my_user_id")
    private long myUserId;

    @Column(name = "friend_user_id")
    private long friendUserId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private boolean deleted;
    @Builder
    public FriendEntity(long id, long myUserId, long friendUserId, LocalDateTime createdAt, boolean deleted) {
        this.id = id;
        this.myUserId = myUserId;
        this.friendUserId = friendUserId;
        this.createdAt = createdAt;
        this.deleted = deleted;
    }

}
