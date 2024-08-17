package com.nexters.gaetteok.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nexters.gaetteok.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        return JWT.create()
            .withClaim("id", user.getId())
            .sign(Algorithm.HMAC256(secret));
    }

}
