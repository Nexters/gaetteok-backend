package com.nexters.gaetteok.persistence.entity;

import com.nexters.gaetteok.domain.AuthProvider;
import com.nexters.gaetteok.weather.enums.City;
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

    @Column(name = "oauth_identifier", unique = true)
    private String oauthIdentifier;

    @Column(name = "device_token")
    private String deviceToken;

    private String nickname;

    private String profileUrl;

    @Column(unique = true)
    private String code;

    @Column
    @Enumerated(EnumType.STRING)
    private City location;

    @Column
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public UserEntity(long id, String oauthIdentifier, String deviceToken, String nickname, String profileUrl, String code, City city, AuthProvider authProvider, LocalDateTime createdAt) {
        this.id = id;
        this.oauthIdentifier = oauthIdentifier;
        this.deviceToken = deviceToken;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.code = code;
        this.location = city;
        this.provider = authProvider;
        this.createdAt = createdAt;
    }

}
