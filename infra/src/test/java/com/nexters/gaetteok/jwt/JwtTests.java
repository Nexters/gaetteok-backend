package com.nexters.gaetteok.jwt;

import com.nexters.gaetteok.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTests {

    private final String SECRET = "secret";

    JwtTokenGenerator jwtTokenGenerator = new JwtTokenGenerator();
    JwtTokenValidator jwtTokenValidator = new JwtTokenValidator(SECRET);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtTokenGenerator, "secret", SECRET);
    }

    @Test
    void jwtTest() {
        // given
        User user = User.builder()
            .id(1L)
            .nickname("포포")
            .profileUrl("https://profile.com/1.jpg")
            .code("1a2b3c")
            .location("서울")
            .build();

        // when
        String token = jwtTokenGenerator.generateToken(user);
        UserInfo decodedUserInfo = jwtTokenValidator.decodeToken(token);

        // then
        assertThat(decodedUserInfo.getUserId()).isEqualTo(user.getId());
    }

}
