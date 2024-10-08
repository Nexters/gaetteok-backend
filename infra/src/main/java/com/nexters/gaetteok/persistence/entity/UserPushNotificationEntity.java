package com.nexters.gaetteok.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "user_push_notification", indexes = {
    @Index(name = "idx_push_notification_time", columnList = "push_notification_time")
}
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPushNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "push_notification_time")
    private Integer pushNotificationTime;

    @Column(name = "is_on", columnDefinition = "boolean default false")
    private boolean isOn;
}
