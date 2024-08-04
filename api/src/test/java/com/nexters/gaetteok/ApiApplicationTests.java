package com.nexters.gaetteok;

import com.nexters.gaetteok.persistence.repository.FriendRepository;
import com.nexters.gaetteok.persistence.repository.UserPushNotificationRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class ApiApplicationTests {

  // TODO : 별도 모듈로 분리된 Spring Data JPA 의존성을 어떻게 다룰 것인가?
  @MockBean
  private UserRepository userRepository;

  @MockBean
  private FriendRepository friendRepository;

  @MockBean
  private UserPushNotificationRepository userPushNotificationRepository;

  @Test
  void contextLoads() {
  }

}
