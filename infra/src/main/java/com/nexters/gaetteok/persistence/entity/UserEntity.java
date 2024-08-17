package com.nexters.gaetteok.persistence.entity;

import com.nexters.gaetteok.weather.enums.City;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Column
    @Enumerated(EnumType.STRING)
    private City location;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public UserEntity(long id, String nickname, String profileUrl, String code, City city,
        LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.code = code;
        this.location = city;
        this.createdAt = createdAt;
    }

}
