package com.nexters.gaetteok.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_push_notification")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPushNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "push_notification_time")
    private int pushNotificationTime;
}
