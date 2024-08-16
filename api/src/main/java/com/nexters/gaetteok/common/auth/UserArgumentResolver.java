package com.nexters.gaetteok.common.auth;

import com.nexters.gaetteok.domain.User;
import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(@Nonnull MethodParameter parameter) {
        return parameter.getParameterType().equals(UserInfo.class);
    }

    @Override
    public Object resolveArgument(@Nonnull MethodParameter parameter, ModelAndViewContainer mavContainer, @Nonnull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // TODO 헤더 내 토큰에서 꺼내온 사용자 식별값. 현재 로그인 기능 미구현으로 임시값 사용
        return new UserInfo(1L);
    }

}
