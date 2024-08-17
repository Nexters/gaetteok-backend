package com.nexters.gaetteok.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenValidator {

    private final JWTVerifier verifier;

    public JwtTokenValidator(@Value("${jwt.secret}") String secret) {
        this.verifier = JWT.require(Algorithm.HMAC256(secret))
            .build();
    }

    public UserInfo decodeToken(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        return UserInfo.builder()
            .userId(decodedJWT.getClaim("id").asLong())
            .build();
    }

}
