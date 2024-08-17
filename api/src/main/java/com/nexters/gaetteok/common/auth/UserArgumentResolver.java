package com.nexters.gaetteok.common.auth;

import com.nexters.gaetteok.common.exception.InvalidTokenException;
import com.nexters.gaetteok.jwt.JwtTokenValidator;
import com.nexters.gaetteok.jwt.UserInfo;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenValidator jwtTokenValidator;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(@Nonnull MethodParameter parameter) {
        return parameter.getParameterType().equals(UserInfo.class);
    }

    @Override
    public Object resolveArgument(@Nonnull MethodParameter parameter, ModelAndViewContainer mavContainer, @Nonnull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)).getHeader("Authorization");
        String token = getTokenFromAuthorizationHeader(authorization);
        UserInfo userInfo = getUserInfoFromToken(token);
        boolean userExists = userRepository.existsById(userInfo.getUserId());
        if (!userExists) {
            throw new InvalidTokenException("존재하지 않는 사용자입니다. userId=" + userInfo.getUserId());
        }
        return userInfo;
    }

    private String getTokenFromAuthorizationHeader(String authorization) {
        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
            throw new InvalidTokenException("올바르지 않은 형식의 토큰입니다. Authorization=" + authorization);
        }
        return authorization.substring(7);
    }

    private UserInfo getUserInfoFromToken(String token) {
        try {
            return jwtTokenValidator.decodeToken(token);
        } catch (Exception e) {
            throw new InvalidTokenException("토큰 디코딩에 실패했습니다. token=" + token);
        }
    }

}
