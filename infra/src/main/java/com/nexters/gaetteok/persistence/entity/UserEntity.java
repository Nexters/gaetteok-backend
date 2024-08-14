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
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;

    private String profileUrl;

    @Column(unique = true)
    private String code;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public UserEntity(long id, String nickname, String profileUrl, String code, LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.code = code;
        this.createdAt = createdAt;
    }

}
