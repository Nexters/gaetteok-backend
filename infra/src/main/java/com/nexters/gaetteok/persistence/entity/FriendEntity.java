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
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "my_user_id")
    private UserEntity me;

    @ManyToOne
    @JoinColumn(name = "friend_user_id")
    private UserEntity friend;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public FriendEntity(long id, UserEntity me, UserEntity friend, LocalDateTime createdAt) {
        this.id = id;
        this.me = me;
        this.friend = friend;
        this.createdAt = createdAt;
    }

}
